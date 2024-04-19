// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Clavier {
   private static BufferedReader flux;

   private Clavier() {
   }

   public static byte saisirByte() {
      byte var0 = 0;
      boolean var1 = true;

      while(var1) {
         try {
            var0 = Byte.valueOf(flux.readLine());
            var1 = false;
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas un byte. Recommencez.");
         }
      }

      return var0;
   }

   public static short saisirShort() {
      short var0 = 0;
      boolean var1 = true;

      while(var1) {
         try {
            var0 = Short.valueOf(flux.readLine());
            var1 = false;
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas un short. Recommencez.");
         }
      }

      return var0;
   }

   public static int saisirInt() {
      int var0 = 0;
      boolean var1 = true;

      while(var1) {
         try {
            var0 = Integer.valueOf(flux.readLine());
            var1 = false;
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas un int. Recommencez.");
         }
      }

      return var0;
   }

   public static long saisirLong() {
      long var0 = 0L;
      boolean var2 = true;

      while(var2) {
         try {
            var0 = Long.valueOf(flux.readLine());
            var2 = false;
         } catch (Exception var4) {
            System.err.println("Erreur : la valeur saisie n'est pas un long. Recommencez.");
         }
      }

      return var0;
   }

   public static double saisirDouble() {
      double var0 = 0.0;
      boolean var2 = true;

      while(var2) {
         try {
            var0 = Double.valueOf(flux.readLine());
            var2 = false;
         } catch (Exception var4) {
            System.err.println("Erreur : la valeur saisie n'est pas un double. Recommencez.");
         }
      }

      return var0;
   }

   public static float saisirFloat() {
      float var0 = 0.0F;
      boolean var1 = true;

      while(var1) {
         try {
            var0 = Float.valueOf(flux.readLine());
            var1 = false;
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas un float. Recommencez.");
         }
      }

      return var0;
   }

   public static char saisirChar() {
      char var0 = ' ';
      boolean var1 = true;

      while(var1) {
         try {
            String var2 = flux.readLine();
            if (var2.length() > 0) {
               var0 = var2.charAt(var2.length() - 1);
               var1 = false;
            }
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas un char. Recommencez.");
         }
      }

      return var0;
   }

   public static String saisirString() {
      String var0 = "";
      boolean var1 = true;

      while(var1) {
         try {
            var0 = flux.readLine();
            var1 = false;
         } catch (Exception var3) {
            System.err.println("Erreur : la valeur saisie n'est pas une chaine. Recommencez.");
         }
      }

      return var0;
   }

   static {
      flux = new BufferedReader(new InputStreamReader(System.in));
   }
}

