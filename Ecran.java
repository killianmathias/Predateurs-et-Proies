// Source code is decompiled from a .class file using FernFlower decompiler.
public class Ecran {
   public Ecran() {
   }

   public static void main(String[] var0) {
      int var1 = Clavier.saisirInt();
      afficherln("salut", var1, 'c');
      afficherln("toto");
      afficherln();
      afficherln(24);
   }

   public static void afficher(Object... var0) {
      Object[] var1 = var0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Object var4 = var1[var3];
         System.out.print(var4.toString());
      }

   }

   public static void afficherln(Object... var0) {
      Object[] var1 = var0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Object var4 = var1[var3];
         System.out.print(var4.toString());
      }

      System.out.println();
   }

   public static void sautDeLigne() {
      System.out.println();
   }
}

