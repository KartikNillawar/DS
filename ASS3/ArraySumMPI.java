import mpi.*;
import java.util.*;

public class ArraySumMPI {
    public static void main(String[] args){
        MPI.Init(args);
        int n = 10;
        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();
        int[] array = new int[n];
        int[] sub_array = new int[n / size];
        int sub_sum = 0, sum = 0;

        if(rank == 0){
            for (int i = 0; i < n; i++) {
                array[i] = i+1;
            }
        }
        MPI.COMM_WORLD.Scatter(array, 0, n/size, MPI.INT, sub_array, 0, n / size, MPI.INT, 0);

        for (int j = 0; j < n / size; j++) {
            sub_sum += sub_array[j];
        }
        int[] recvbuf = new int[1];
        MPI.COMM_WORLD.Reduce(new int[] { sub_sum }, 0, recvbuf, 0, 1, MPI.INT, MPI.SUM, 0);
        if (rank == 0) {
            sum = recvbuf[0];
            System.out.println("The sum of the array is " + sum);
        }
        MPI.Finalize();
    }
}
