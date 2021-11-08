import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



/**
 * Main method
 * Reads in matrix, creates adjacency list, finds BFS tree
 *
 * @author Isaac J. Nuno
 * @version 2.0, 06 Nov 2021
 * Updated Adjacency list so that it actually holds nodes and data not just a list of integers.
 * Updated BFS tree so that is uses nodes to navigate to the next node.
 */
public class BFSTest {
    public static void main(String[] args){
        userMenu();
    }
    /**
     *User Options
     */
    public static void userMenu()
    {

        do {
            System.out.printf("%s", "Enter file path: ");
            Scanner scnr = new Scanner(System.in);
            String filePath = scnr.nextLine();
            String matrix = copyMatrix(filePath);
            LinkedList<Node<Integer>> adjList = createAdjacencyList(filePath);
            printMatrix(matrix);
            printAdjList(adjList);
            printBFSTree(adjList);
            printOptions();

            String userInput;
            do {
                userInput = scnr.nextLine().toLowerCase();
                switch (userInput) {
                    case "1":
                        printMatrix(matrix);
                        printOptions();
                        break;
                    case "2":
                        printAdjList(adjList);
                        printOptions();
                        break;
                    case "3":
                        printBFSTree(adjList);
                        printOptions();
                        break;
                    case "4":  //Enter new graph, exit while loop
                        break;
                    case "e":
                        System.out.printf("%s\n", "---------------End of program---------------");
                        System.exit(-1);
                    default:
                        System.out.printf("%s\n", "Choose an option from the list: ");
                        printOptions();
                        break;
                }
            } while (!userInput.equals("4"));
        }while(true);
    }

    /**
     * Creates Adjacency list from Matrix
     *
     * @param   filePath file path to text document
     * @return  Adjacency List
     */
    public static LinkedList<Node<Integer>> createAdjacencyList(String filePath)
    {
        LinkedList<Node<Integer>> adjList = new LinkedList<>();      //List to hold list of connected nodes
        String row;
        try
        {
            int y = 0;

            File inputFile = new File(filePath);
            Scanner fileInput = new Scanner(inputFile);
            row = fileInput.nextLine().replace(" ","");
            for(int i = 0;i < row.length(); ++i)                    //for N nodes
            {
                adjList.add(new Node<Integer>(i));                  //Add all nodes
            }
            Node<Integer> temp;
            while(fileInput.hasNext())
            {
                for (int x = y + 1; x < row.length(); ++x)   //A node doesn't have an edge to itself therefore x = y + 1
                {
                    if (row.charAt(x) == '1') {
                        temp = adjList.get(y);            //If node a is linked to b, add b to nodes that a is linked to
                        temp.children.add(adjList.get(x));
                        temp = adjList.get(x);            //If node b is linked to a, add a to nodes that b is linked to
                        temp.children.add(adjList.get(y));
                    }
                }
                row = fileInput.nextLine().replace(" ","");
                ++y;                                                //current row

            }
            fileInput.close();

        }catch(FileNotFoundException e){System.out.printf("%s", "File not found. Check path.");}
        catch(InputMismatchException e){System.out.printf("%s", "Check file for bad data.");}
        return adjList;
    }

    /**
     * Saves Matrix read from text file as a String
     *
     * @param   filePath path to text file
     * @return  Matrix string
     */
    public static String copyMatrix(String filePath)
    {
        StringBuilder temp = new StringBuilder();
        try
        {
            File inputFile = new File(filePath);
            Scanner fileInput = new Scanner(inputFile);
            while(fileInput.hasNext())
            {
                temp.append(fileInput.nextLine()).append("\n");     //appends line read from text to temp string
            }
            fileInput.close();
        }catch(FileNotFoundException e){System.out.printf("%s", "File not found. Check path.");}
        catch(InputMismatchException e){System.out.printf("%s", "Check file for bad data.");}
        return temp.toString();

    }

    /**
     * Prints Matrix saved from text file
     * @param matrix string
     */
    public static void printMatrix(String matrix)
    {
        System.out.printf("%40s\n%s", "Matrix representation of input graph", "********************************************\n");
        System.out.printf("%s\n", matrix);
    }


    /**
     *Prints Adjacency List derived from Matrix
     *
     * @param adjList Adjacency List
     */
    public static void printAdjList(LinkedList<Node<Integer>> adjList)
    {
        System.out.printf("%s\n", "*********************************************");
        System.out.printf("%s%-20s\n\n", "Number of nodes in graph: ", adjList.size());
        System.out.printf("%s\n", "---------Adjacency List---------");

        for (Node<Integer> node : adjList) {                        //For every node A
            System.out.printf("%s %s %s ", "Node", node.data, ":");    //Print node A
            for(Node<Integer> child : node.children) {
                System.out.printf("%s ", child.data);               //Print all nodes connected to node A
            }
            System.out.printf("%s\n", "");                           //new line
        }
        System.out.printf("%s\n\n", "");

    }

    /**
     * Breadth First Search using Adjacency List
     *
     * @param adjList Adjacency List
     */
    public static void printBFSTree(LinkedList<Node<Integer>> adjList)
    {

        int s;                                                         //Starting node
        Set<Node<Integer>> Discovered = new HashSet<>();               //List of visited nodes
        LinkedList<Node<Integer>> queue = new LinkedList<>();          //Queue to keep track of which node to visit next

        System.out.printf("\n%s\n", "---------BFS Tree---------");
        System.out.printf("%s", "Select the starting node: ");
        Scanner scnr = new Scanner(System.in);
        s = scnr.nextInt();                                             //Root

        while(s < 0 || s > adjList.size() - 1)
        {
            System.out.printf("%s%s : ", "Select node within range: 0 to ", adjList.size() - 1);
            s = scnr.nextInt();
        }

        System.out.printf("\n\n%s\n","---------BFS Tree---------");
        System.out.printf("%s %s\n","Parent:","Child");

        Discovered.add(adjList.get(s));                                //Add root to Discovered Set
        queue.add(adjList.get(s));                                     //add root to queue
        Node<Integer> temp = null;

        while(!queue.isEmpty())                                        //while queue is not empty
        {
            if(!queue.isEmpty())
                temp = queue.poll();                                   //Next node
            System.out.printf("%6s: ", temp.data);                     //Prints parent node
            for(Node<Integer> child : temp.children)                   //For each node
            {
                if(!Discovered.contains(child))
                {
                    queue.add(child);                                  //Add undiscovered node to queue
                    Discovered.add(child);                             //Add discovered node to Set
                    System.out.printf("%s ", child.data);              //Prints all children of current parent node
                }
            }

            System.out.printf("%s\n", "");

        }
    }


    public static void printOptions()
    {
        System.out.print("\n\n\n**************************************************"+"\n"+
                "Please select from one of the following options: \n\n"+
                "(1) View Matrix\n"+
                "(2) View Adjacency List\n"+
                "(3) BFS Search\n"+
                "(4) New Matrix\n"+
                "(E) Exit\n");
    }
}
