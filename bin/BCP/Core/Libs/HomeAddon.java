/*    */ package BCP.Core.Libs;
/*    */ 
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ public class HomeAddon
/*    */ {
/*    */   public static void save(Object obj, String path) throws Exception
/*    */   {
/* 10 */     ObjectOutputStream oos = new ObjectOutputStream(new java.io.FileOutputStream(path));
/* 11 */     oos.writeObject(obj);
/* 12 */     oos.flush();
/* 13 */     oos.close();
/*    */   }
/*    */   
/* 16 */   public static Object load(String path) throws Exception { ObjectInputStream ois = new ObjectInputStream(new java.io.FileInputStream(path));
/* 17 */     Object result = ois.readObject();
/* 18 */     ois.close();
/* 19 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/robin/Schreibtisch/BCP V1.1 Build #004.jar!/BCP/Core/Libs/HomeAddon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */