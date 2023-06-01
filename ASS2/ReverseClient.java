import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

class ReverseClient {
    public static void main(String args[]) {
        Reverse ReverseImpl = null;
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            String name = "Reverse";
            ReverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Enter String=");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            String tempStr = ReverseImpl.reverse_string(str);
            System.out.println(tempStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
