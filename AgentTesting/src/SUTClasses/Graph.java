package SUTClasses;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
public class Graph implements Runnable
{
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
    
    

 
    public void setEdge(int from, int to)
    {
        if (to > adjacencyList.size() || from > adjacencyList.size())
            System.out.println("The vertices does not exists");
        /*
         * List<Integer> sls = adjacencyList.get(to);
         * sls.add(from);
         */
        List<Integer> dls = adjacencyList.get(from);
        dls.add(to);
    }
 
    public List<Integer> getEdge(int to)
    {
        /*
         * if (to > adjacencyList.size())
         * {
         * System.out.println("The vertices does not exists");
         * return null;
         * }
         */
        return adjacencyList.get(to);
    }
 
    public Graph checkDAG()
    {
        Integer count = 0;
        Iterator<Integer> iteratorI = this.adjacencyList.keySet().iterator();
        Integer size = this.adjacencyList.size() - 1;
        System.out.println("Minimal set of edges: ");
        while (iteratorI.hasNext())
        {
            Integer i = iteratorI.next();
            List<Integer> adjList = this.adjacencyList.get(i);
            if (count == size)
            {
                return this;
            }
            if (adjList.size() == 0)
            {
                count++;
                Iterator<Integer> iteratorJ = this.adjacencyList.keySet()
                        .iterator();
                while (iteratorJ.hasNext())
                {
                    Integer j = iteratorJ.next();
                    List<Integer> li = this.adjacencyList.get(j);
                    if (li.contains(i))
                    {
                        li.remove(i);
                        System.out.println(i + " -> " + j);
                    }
                }
                this.adjacencyList.remove(i);
                iteratorI = this.adjacencyList.keySet().iterator();
            }
        }
        return this;
    }
 
    public Map<Integer, List<Integer>> getFeedbackArcSet(int v)
    {
        int[] visited = new int[v + 1];
        Iterator<Integer> iterator = this.adjacencyList.keySet().iterator();
        Map<Integer, List<Integer>> l = new HashMap<Integer, List<Integer>>();
        while (iterator.hasNext())
        {
            Integer i = iterator.next();
            List<Integer> list = this.adjacencyList.get(i);
            visited[i] = 1;
            if (list.size() != 0)
            {
                for (int j = 0; j < list.size(); j++)
                {
                    if (visited[list.get(j)] == 1)
                    {
                        l.put(i, new LinkedList<Integer>());
                        l.get(i).add(j);
                    }
                    else
                    {
                        visited[list.get(j)] = 1;
                    }
                }
            }
        }
        return l;
    }
 
    public void printGraph()
    {
        System.out.println("The Graph is: ");
        Iterator<Integer> iterator = this.adjacencyList.keySet().iterator();
        while (iterator.hasNext())
        {
            Integer i = iterator.next();
            List<Integer> edgeList = this.getEdge(i);
            if (edgeList.size() != 0)
            {
                System.out.print(i);
                for (int j = 0; j < edgeList.size(); j++)
                {
                    System.out.print(" -> " + edgeList.get(j));
                }
                System.out.println();
            }
        }
    }

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++)
            adjacencyList.put(i, new LinkedList<Integer>());
		
	}
}
 
