"""
Auto-Fix Links Tool

Usage:
    1. Dry Run (Scan only, no changes):
       python3 auto-fix-links.py

    2. Fix Links (Apply changes):
       python3 auto-fix-links.py --fix

Description:
    This script automatically scans all Markdown files in the directory where it is located (and subdirectories).
    It identifies broken links (images, files) and attempts to find the correct file by searching the directory tree.
    If a match is found, it can update the link to the correct relative path.
"""
import os
import re
import argparse
import difflib
from urllib.parse import unquote, quote
import sys
# Configuration
DEFAULT_EXTENSIONS = ('.md', '.markdown')
ASSET_EXTENSIONS = ('.png', '.jpg', '.jpeg', '.gif', '.svg', '.pdf', '.sql')
def index_directory(root_dir):
    """
    Walks the directory and returns a map of {filename: [absolute_paths]}.
    Handles duplicates by storing a list of paths.
    """
    file_map = {}
    print(f"Indexing files in {root_dir}...")
    for root, dirs, files in os.walk(root_dir):
        # Skip hidden directories like .git
        dirs[:] = [d for d in dirs if not d.startswith('.')]
        
        for file in files:
            if file.startswith('.'): continue
            
            # optional: filter by extensions if we only care about linking to certain things
            # but usually we want to link to anything.
            
            abs_path = os.path.join(root, file)
            if file not in file_map:
                file_map[file] = []
            file_map[file].append(abs_path)
            
    return file_map
def get_relative_path(source_file, target_file):
    """
    Returns the relative path from source_file (directory) to target_file.
    """
    source_dir = os.path.dirname(source_file)
    rel_path = os.path.relpath(target_file, source_dir)
    return rel_path
def find_best_match(filename, file_map):
    """
    Finds the best matching file in the map.
    Returns the absolute path or None.
    """
    if filename in file_map:
        candidates = file_map[filename]
        # If there are multiple, we pick the first one (or could ask user, but this is auto-mode)
        # Ideally, prefer one that is "closest" in the tree, but for now simple 1st match.
        return candidates[0]
    
    # Fuzzy match?
    # Only fuzzy match if exact match fails
    matches = difflib.get_close_matches(filename, file_map.keys(), n=1, cutoff=0.6)
    if matches:
        return file_map[matches[0]][0]
        
    return None
def process_file(filepath, file_map, fix_mode, root_dir):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Pattern for [text](url) or ![text](url)
    # Group 1: text, Group 2: url
    pattern = re.compile(r'(\[[^\]]*\])\(([^)]+)\)')
    
    new_content = content
    modifications = []
    
    # We iterate in reverse order or use a replacement function to avoid offsetting indices
    # But re.sub with a callback is cleaner.
    
    def replacement_handler(match):
        full_match = match.group(0)
        link_text = match.group(1)
        original_url = match.group(2)
        
        # 1. Ignore anchors, external links
        if original_url.startswith(('http://', 'https://', 'mailto:', '#', 'ftp://')):
            return full_match
            
        # 2. Decode URL
        decoded_url = unquote(original_url)
        
        # 3. Check if it exists relative to current file
        source_dir = os.path.dirname(filepath)
        
        # Cases:
        # A. Absolute path (from root) /foo/bar.md -> check root_dir + foo/bar.md
        # B. Relative path ../foo.md -> check source_dir + ../foo.md
        
        search_paths = []
        if decoded_url.startswith('/'):
            # Assuming / means relative to the project root (root_dir) passed in args
            # Try to resolve relative to root_dir
            search_paths.append(os.path.join(root_dir, decoded_url.lstrip('/')))
        else:
            search_paths.append(os.path.join(source_dir, decoded_url))
            
        exists = False
        for p in search_paths:
            if os.path.exists(p):
                exists = True
                break
        
        if exists:
            return full_match
            
        # 4. If not exists, try to find a fix
        # Extract filename
        basename = os.path.basename(decoded_url)
        
        # Special case: URL might have query params or anchors? Link checker usually ignores them or strips them
        # Let's strip anchor for lookup
        if '#' in basename:
            basename, anchor = basename.split('#', 1)
            anchor = '#' + anchor
        else:
            anchor = ''
            
        target_abs_path = find_best_match(basename, file_map)
        
        if target_abs_path:
            # We found a candidate!
            # Calculate new relative path
            new_rel_path = get_relative_path(filepath, target_abs_path)
            
            # URL Encode the new path (space -> %20)
            # Use quote, but don't quote slashes usually in path
            # But standard is %20 for space.
            encoded_new_path = quote(new_rel_path) + anchor
            
            print(f"[FIX] {os.path.basename(filepath)}: '{original_url}' -> '{encoded_new_path}'")
            return f"{link_text}({encoded_new_path})"
        else:
            print(f"[MISS] {os.path.basename(filepath)}: Could not find target for '{original_url}'")
            return full_match
    if fix_mode:
        new_content = pattern.sub(replacement_handler, content)
        if new_content != content:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(new_content)
            print(f"Updated {filepath}")
    else:
        # Dry run mode - just use finditer to report
        for match in pattern.finditer(content):
            replacement_handler(match)
def main():
    parser = argparse.ArgumentParser(description="Auto-fix broken Markdown links.")
    parser.add_argument("--fix", action="store_true", help="Apply fixes to files. If not set, runs in dry-run mode.")
    
    args = parser.parse_args()
    
    # Auto-detect directory: Use the folder where this script is located
    target_dir = os.path.dirname(os.path.abspath(__file__))
    root_dir = target_dir
    
    print(f"Running in: {target_dir}")
    
    if not os.path.isdir(target_dir):
        print(f"Error: {target_dir} is not a directory.")
        sys.exit(1)
        
    # 1. Index context
    file_map = index_directory(root_dir)
    
    # 2. Process Files
    print(f"Scanning {target_dir}...")
    count = 0
    for root, dirs, files in os.walk(target_dir):
        for file in files:
            if file.endswith(DEFAULT_EXTENSIONS):
                filepath = os.path.join(root, file)
                process_file(filepath, file_map, args.fix, root_dir)
                count += 1
                
    print(f"Scanned {count} files.")
if __name__ == "__main__":
    main()