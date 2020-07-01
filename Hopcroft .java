import java.util.*;
 
public class Hopcroft {    
    private final int SUL = 0;
    private final int DEL = Integer.MAX_VALUE;
    private ArrayList<Integer>[] Adj; 
    private int[] Par;
    private int[] Dist;
    private int dx, bx;
 
   
    public boolean BFS() 
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= dx; ++i) 
            if (Par[i] == SUL) 
            { 
                Dist[i] = 0; 
                queue.add(i); 
            }
            else 
                Dist[i] = DEL;
 
        Dist[SUL] = DEL;
 
        while (!queue.isEmpty()) 
        {
            int i = queue.poll();
            if (Dist[i] < Dist[SUL]) 
                for (int j : Adj[i]) 
                    if (Dist[Par[j]] == DEL) 
                    {
                        Dist[Par[j]] = Dist[i] + 1;
                        queue.add(Par[j]);
                    }           
        }
        return Dist[SUL] != DEL;
    }    
     
    public boolean PRI(int i) 
    {
        if (i != SUL) 
        {
            for (int j : Adj[i]) 
                if (Dist[Par[j]] == Dist[i] + 1)
                    if (PRI(Par[j])) 
                    {
                        Par[j] = i;
                        Par[i] = j;
                        return true;
                    }               
 
            Dist[i] = DEL;
            return false;
        }
        return true;
    }

    public int Hopcrof() 
    {
        Par = new int[dx + bx + 1];
        Dist = new int[dx + bx + 1];
        int matching = 0;
        while (BFS())
            for (int i = 1; i <= dx; ++i)
                if (Par[i] == SUL)
                    if (PRI(i))
                        matching = matching + 1;
        return matching;
    }
   
    public void grafico(int[] x, int[] y, int E)
    {
        Adj = new ArrayList[dx + bx + 1];
        for (int i = 0; i < Adj.length; ++i)
            Adj[i] = new ArrayList<Integer>();        
        /** adding edges **/    
        for (int i = 0; i < E; ++i) 
            addbeira(x[i] + 1, y[i] + 1);    
    }

    public void addbeira(int j, int i) 
    {
        Adj[j].add(dx + i);
        Adj[dx +i].add(i);
    }    
    
    public static void main (String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hopcroft\n");
      
        Hopcroft  teoria = new Hopcroft ();
 
   
        System.out.println("Entre com numero\n");
        int E = scan.nextInt();
        int[] x = new int[E];
        int[] y = new int[E];
        teoria.dx = 0;
        teoria.bx = 0;
 
        System.out.println("entrar "+ E +" x, y coordenadas ");
        for (int i = 0; i < E; i++)
        {
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
            teoria.dx = Math.max(teoria.dx, x[i]);
            teoria.bx = Math.max(teoria.bx, y[i]);
        }
        teoria.dx += 1;
        teoria.bx += 1;
 
      
        teoria.grafico(x, y, E);            
 
        System.out.println("\nPartidas : "+ teoria.Hopcrof());            
    }    
}
