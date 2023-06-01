import ReverseModule.Reverse;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class ReverseServer {
    public static void main(String[] args) {
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            ReverseImpl rvr = new ReverseImpl();
            Reverse h_ref = ReverseModule.ReverseHelper.narrow(rootPOA.servant_to_reference(rvr));
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            ncRef.rebind(ncRef.to_name("Reverse"), h_ref);
            orb.run();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
