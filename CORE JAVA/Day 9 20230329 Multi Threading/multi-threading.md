# Multi-Threading

## Multi-tasking

**What is multi-tasking**: performing more than one task simultaneously.

a) Process-based multi-tasking
b) Thread-based multi-tasking

**Process** - is a program in execution.
**Thread** - is one of the parts of a program in execution.

1.  **Process-based multitasking**: More than one process is running simultaneously.
    *   *Example*: Word and Excel applications are running simultaneously.
2.  **Thread-based multitasking**: More than one thread is running simultaneously.
    *   *Example*: Within a Word application, spell check, editing, cloud sync etc. all are running at the same time.

> Whether process-based or thread-based, a CPU can handle only one task at a time, unless it is a multiprocessor machine. It is just an impression given to the user. What actually CPU does is **context switching**, i.e., jump from one task to another and vice-versa.

### Process-based vs Thread-based

a) Threads can share the memory, processes can not.
b) Context switching between threads is relatively cheaper compared to that between processes.
c) Cost of communication between threads is also low.
*(Cheaper or low cost -> actually means less number of system resources are used.)*

**Java supports thread-based multitasking.**

**Application of multi-threading in Java**: Due to the multithreading feature, Java has become effective on the server side. E.g., Servlet, JSP etc.

### Thread-Scheduler
a) Pre-emptive
b) Time-slice

JVM can have any scheduler, i.e., either pre-emptive or time-slice. It is because JVM is different for different platforms.
Java has given certain mechanisms (functions) whereby you can make sure your multi-threading application can run more or less same on any OS.

### Important steps required for a multithreading application
1.  Create thread/s
2.  Define thread execution body
3.  Register thread with the thread scheduler
4.  Thread scheduler will execute the thread/s

---

## Java's Multi-threading Support

Java's support lies in:
a) `java.lang.Thread`
b) `java.lang.Runnable` (interface)
c) `java.lang.Object`

### Thread Class
This is the most important class required in order to create a multi-threading application. Following are its methods:

*   **start**: Is used to register thread with JVM scheduler.
*   **run**: Is used by the programmer to define thread execution body, but will be called by JVM scheduler whenever it executes a particular thread. When the run method is over, the thread is dead.
*   **sleep** (static): Is used to make thread sleep for some time.
*   **setName**: To set the name of thread.
*   **getName**: To get the name of thread.
*   **currentThread**: Returns the currently running thread.
*   **setPriority**: To set the priority.
    *   In Java, priorities are numbers from 1 to 10.
    *   1 - Minimum priority
    *   5 - Normal priority
    *   10 - Maximum priority
    *   *By default, every thread created has normal priority.*
    *   *(Imp: priorities are not guaranteed across different platforms.)*
*   **getPriority**: To get the priority.
*   **join**: `join()` method is used for waiting for the thread in execution until the thread on which join is called is completed.

### Runnable Interface
Interface which contains an abstract method:
```java
void run();
```

---

## Creation of Multi-threading Application

There are two ways to create a multi-threading application:
1.  `extends Thread`
2.  `implements Runnable`

### 1. Extends Thread

```java
public class Th1 extends Thread
{
    public void run()
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        Th1 t1 = new Th1();
        t1.start();
    }
}
```

> By default, every Java application has a **main thread** created by JVM. This thread is used to execute the `main()` function.

In the above code, there are 2 threads:
1.  Main thread
2.  User defined thread i.e., `t1`

Hence there are 2 call stacks in the above code. One for `main()` and other for `t1` (`run()` method).

When main function is over, main thread dies, but user defined thread/s can continue. They will be taken care of by JVM.
i.e., in the above code, after `t1.start()` when `main()` function is over, main thread dies, but `t1`'s execution will be managed by JVM.

#### Example 2: Setting Names

```java
public class Th2 extends Thread
{
    public void run()
    {
        System.out.println(Thread.currentThread());
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        System.out.println(Thread.currentThread());
        Th2 t1 = new Th2();
        t1.setName("first");
        t1.start();
    }
}
```

#### Can we call run() directly?

```java
public class Th3 extends Thread
{
    public void run()
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        Th3 t1 = new Th3();
        t1.setName("first");
        t1.run();
    }
}
```

> **Note**: We can call `run()` directly. But in that case it won't be thread execution, it is a normal method call. That implies different call stacks won't be created.

#### Multiple User Defined Threads

```java
public class Th4 extends Thread
{
    public void run()
    {
        System.out.println(Thread.currentThread());
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        System.out.println(Thread.currentThread());
        Th4 t1 = new Th4();
        Th4 t2 = new Th4();
        t1.setName("first");
        t2.setName("second");
        t1.start();
        t2.start();
    }
}
```

#### Example with Sleep

```java
public class Th4_a extends Thread
{
    public void run()
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello  " + Thread.currentThread().getName() + "\t" + i);
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        Th4_a t1 = new Th4_a();
        Th4_a t2 = new Th4_a();
        t1.setName("first");
        t2.setName("second");
        t1.start();
        t2.start();
    }
}
```

### 2. Implements Runnable

```java
public class Th5 implements Runnable
{
    public void run()
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        Th5 ob = new Th5();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob);
        t1.start();
        t2.start();
    }
}
```

**Steps:**
a) Define a class which implements `Runnable`
b) Define `run()`
c) Instantiate the class which implements `Runnable`
d) Instantiate `Thread` class by passing above instance (child of `Runnable`)
e) Register `Thread` class instance/s

#### Difference between `extends Thread` and `implements Runnable`

**How is Thread class related to Runnable interface?**
Ans: `Thread` class implements `Runnable`.

**What is the use of `implements Runnable`?**
If your class is already extending some class, you can't say `extends Thread`, because multiple inheritance is not allowed in Java. In that case, you have to go for `implements Runnable`.

---

## Synchronization

The above programs prove that threads can share the memory.
When threads share the memory there is a risk of **"Race Condition"**.

**Example:**
There are 2 threads.
- One thread is reading from a file.
- Other thread is writing to a file.

**Race condition** means while one thread is reading from a file, the other thread might write in a file or vice-versa. Race condition always leads to **Data Corruption**.

**How do we avoid Race condition?**
We have to make sure that while one thread is working on data, the other thread should not run. Only after the first thread completes its job, the other thread should start its execution. In Java, we can achieve this by using **"synchronization"**.

> "synchronization" is a solution to the race condition.

### Synchronized Method

```java
public class Th6 implements Runnable
{
    synchronized public void run()
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Hello" + i);
        }
    }
    public static void main(String args[])
    {
        Th6 ob = new Th6();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob);
        t1.start();
        t2.start();
    }
}
```

### Synchronized Block

```java
public class Th7 implements Runnable
{
    public void run()
    {
        synchronized(this)
        {
            for(int i=0; i<5; i++)
            {
                System.out.println("Hello" + i);
            }
        }
    }
    public static void main(String args[])
    {
        Th7 ob = new Th7();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob);
        t1.start();
        t2.start();
    }
}
```

**Synchronized keyword:**
*   **Method**: All the statements are protected.
*   **Block**: Only those statements are protected which are given inside the synchronized block.

### Object Lock (Monitor)

**What exactly happens when we use synchronized keyword?**
There is a concept of **object lock**.

In Java, every object has a lock. This lock can be accessed by only one thread at a time. The lock will be released as soon as the thread completes its job and thus another thread can acquire the lock.
*   This lock comes into picture only when the object has got non-static synchronized method/s or block.
*   Whichever thread executes the synchronized method first, it acquires the lock. Other thread/s have to be in "seeking lock state".
*   Acquiring and Releasing lock happens automatically.
*   Once a thread acquires a lock on an object, it can have control on all the non-static synchronized methods of that object.

**Example with distinct objects:**

```java
public class Th8 implements Runnable
{
    public void run()
    {
        synchronized(this)
        {
            for(int i=0; i<5; i++)
            {
                System.out.println("Hello" + i);
            }
        }
    }
    public static void main(String args[])
    {
        Th8 ob = new Th8();
        Th8 ob1 = new Th8();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob1);
        t1.start();
        t2.start();
    }
}
```

---

## Inter-thread Communication

Even though synchronized method or block is used to avoid "Race Condition", there can be a danger of **"Deadlock"** inside it.
E.g., if one thread is working inside a synchronized block or method and if it gets stuck! Imagine what will happen?
Neither this thread can complete and release the lock, nor can another thread acquire the lock.

**Solution**: Communication between threads.
If the thread realizes it can not continue, it should come out of synchronized method or block and release the lock. Now another thread will acquire the lock, execute the code AND allow the first thread to resume.

Following are the methods used for communication between threads:

a) **wait**: It will make thread release the lock and go to wait pool.
b) **notify**: It will make the thread move from wait pool to seeking lock state.
c) **notifyAll**: It will make all the threads move from wait pool to seeking lock state.

> These methods are defined in `java.lang.Object` class and are `final` so you cannot override them.
> These methods **must be called from synchronized method or block**.

**Difference between wait and sleep**
*   `wait` releases the lock on an object.
*   `sleep` does not.

---

## Thread Safety & Class Locks

**Thread-safe classes** are those classes which contain synchronized non-static methods.

**What is class lock?**
Every class has a lock. It is actually a lock on an instance of class `Class`. This is because whenever any class is loaded in Java, it is represented by an instance of class `Class`.
The class lock comes into picture in case of **synchronized static methods**.
Thread which gives a call to synchronized static method can acquire a class lock. Only after the thread completes that static method, the lock is released.

---

## Join Method

```java
public class Th9 implements Runnable
{
    public void run()
    {
        synchronized(this)
        {
            for(int i=0; i<5; i++)
            {
                System.out.println("Hello" + i);
            }
        }
    }
    public static void main(String args[])
    {
        Th9 ob = new Th9();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob);
        t1.start();
        t2.start();

        System.out.println("Both the threads are over");
    }
}
```

In the above code "Both the threads are over" will not be displayed in the end because it is a statement of `main`. It is because as we know, main thread completes first and user defined threads continue (taken care of by JVM).

If we want that "Both the threads are over" should be displayed at the end, we have to make sure that main thread will complete only after the completion of `t1` and `t2`.

**Solution is `join()` method.**

`join` method makes caller thread (main thread) to wait for called thread (t1 and t2) to complete.

```java
public class Th9_a implements Runnable
{
    public void run()
    {
        synchronized(this)
        {
            for(int i=0; i<5; i++)
            {
                System.out.println("Hello" + i);
            }
        }
    }
    public static void main(String args[])
    {
        Th9 ob = new Th9();
        Th9 ob1 = new Th9();
        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob1);
        t1.start();
        t2.start();
        try
        {
            t1.join();
            t2.join();
        }
        catch(InterruptedException e)
        {
        }
        System.out.println("Both the threads are over");
    }
}
```

In the above code, when `main()` function calls `t1.join()` for example, it says "join me at your end".
Since `main()` is calling `t1.join()` and `t2.join()`, it is added to the end of both `t1` and `t2`. That's why now the statement "Both the threads are over" is getting executed at the end.

> Whenever thread is in a **blocked state** i.e. due to `sleep`, `join` or `wait` methods, it can get interrupted by other threads. Whenever blocked thread gets interrupted, it raises `InterruptedException`.
> But this can not be predictable, hence Java enforces you to either handle or declare `InterruptedException` whenever you invoke the above methods.

---

## Thread States

1.  Born
2.  Runnable
3.  Running
4.  Blocked
5.  Dead

---

## User Threads and Daemon Threads

**User threads**:
*   User defined threads
*   Main thread

**Daemon thread**:
*   E.g., Garbage collection thread (low priority thread)

Daemon threads are the threads which are at the mercy of user thread/s. Their only purpose is to serve user defined thread/s. When there is no user thread alive, Daemon thread will die.

### Example of Garbage Collection Thread

```java
public class Sample
{
    public static void main(String[] args) {
        Sample s1 = new Sample();
        s1 = null;
        System.gc();
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

        //Runtime.getRuntime().gc();
        System.out.println("Done By\t" + Thread.currentThread().getName());
    }

    protected void finalize() throws Throwable
    {
        System.out.println("inside finalized method");
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().isDaemon());
    }
}
```

**Output:**
```
inside finalized method
Thread[Finalizer,8,system]
true
Done by main
```

**Difference between `System.gc()` and `Runtime.getRuntime().gc()`**

Inside `System` class we have following code:

```java
public static void gc()
{
    Runtime.getRuntime().gc();
}
```
