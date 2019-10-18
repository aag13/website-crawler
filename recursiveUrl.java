
import java.io.BufferedWriter;
import java.util.*;

public class recursiveUrl {

    static void printTree(Node node, String space) {
        try {
            
            if (node.name.equals("http://www.theguardian.com")) {
                news.bw.append(space + "ROOT");
                news.bw.newLine();
                System.out.println(space + "ROOT");
            } else {
                news.bw.append(space + node.name);
                news.bw.newLine();
                
                System.out.println(space + node.name);
            }

            for (int i = 0; i < node.children.size(); i++) {
                printTree((Node) node.children.get(i), space + "   ");
                if (node.name.equals("http://www.theguardian.com")) {
                    news.bw.append("========================");
                    news.bw.newLine();
                    System.out.println("========================");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void buildTree(Node parent, StringTokenizer st) {

        if (st.countTokens() == 0) {
            return;
        } else {
            String branch = st.nextToken();
            Node node = new Node(branch,parent.level+1);
            Node n;
            int index = exist(parent.children, node);
            if (index == -1) {
                parent.children.add(node);
                n = node;
            } else {
                n = (Node) parent.children.get(index);
            }
            buildTree(n, st);
        }

    }

    static int exist(LinkedList<Node> ll, Node node) {

        String s1 = node.name;
        for (int i = 0; i < ll.size(); i++) {
            String s2 = ll.get(i).name;
            if (s1.equals(s2)) {
                return i;
            }
        }

        return -1;
    }

}
