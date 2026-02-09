package mypack;

import org.apache.log4j.Logger;
import java.sql.*;
import java.io.*;

public class Demo4{
   /* Get actual class name to be printed on */
   static Logger log = Logger.getLogger(Demo4.class.getName());
   
   public static void main(String[] args)throws IOException,SQLException{
      log.debug("Debug");
      log.info("info");
      log.error("error");
   }
}

