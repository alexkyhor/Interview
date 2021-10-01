import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConnectedCity {

    // function: given edge file, return whether the path between start & end nodes exist
    // usage: java ConnectedCity.java file.txt start_node end_node

    // 1. parsing file for nodes store in map for ID
    // 2. create map to store NB of each node
    // 3. use BFS to search from the start node by Queue
    // 4. until the target node found or the queue is empty

    public static void main(String[]args){

        String fileName = args[0];
        String start = args[1];
        String end = args[2];
        ArrayList<String>edges = parseEdges(fileName);
        HashMap<String,ArrayList<String>> nbMap = nbMaps(edges);

        findConnectV2(start,end,nbMap);


    }

    public static void findConnectV2(String start, String end,
                                     HashMap<String,ArrayList<String>> nbMap){

        long t1 = System.currentTimeMillis();

        Queue<String> search = new PriorityQueue<>();
        ArrayList<String>visited = new ArrayList<>();
        boolean foundPath = end.equals(start);

        if (nbMap.containsKey(start)&&nbMap.containsKey(end)){
            search.add(start);
        }

        while(!search.isEmpty() && !foundPath){
            String searching = search.remove();
            if (!visited.contains(searching)){
                visited.add(searching);
                for (String nb:nbMap.get(searching)) {
                    if (!visited.contains(nb) && !search.contains(nb)){
                        search.add(nb);
                        foundPath = nb.equals(end);
                        if (foundPath){
                            break;
                        }
                    }
                }
            }
        }

        long t2 = System.currentTimeMillis();
        long elapsedTime = t2 - t1;

        System.out.println(foundPath?"Yes":"No");

        System.out.println("Time used: " + elapsedTime);

    }


    public static HashMap<String,ArrayList<String>> nbMaps(ArrayList<String>eds){
        HashMap<String,ArrayList<String>> place2NBs = new HashMap<>();
        for(String e : eds){
            String st = e.split("-")[0];
            String ed = e.split("-")[1];
            if (!place2NBs.containsKey(st)){
                place2NBs.put(st,new ArrayList<>());
            }
            place2NBs.get(st).add(ed);
            if (!place2NBs.containsKey(ed)){
                place2NBs.put(ed,new ArrayList<>());
            }
            place2NBs.get(ed).add(st);
        }
        return place2NBs;
    }


    public static ArrayList<String> parseEdges(String fileName){
        ArrayList<String>edges = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] a = line.split(", ");
                edges.add(a[0]+"-"+a[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return edges;
    }


}
