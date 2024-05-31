public class Main {
    public static void main(String[] args) {
        Patricia patricia = new Patricia();
        patricia.insert("gato");
        patricia.insert("so");
        patricia.insert("sola");
        patricia.insert("solado");
        patricia.insert("solo");
        patricia.insert("sabao");
        patricia.insert("subindo");
        patricia.insert("sol");
        patricia.insert("solda");
        patricia.insert("sala");
        patricia.insert("soleno");
        patricia.insert("sa");
        patricia.insert("sal");
        patricia.showWords();
        System.out.println("----");
        Node node = patricia.getWord("sa");
        if(node != null)
            System.out.println(node.getWord());
        else
            System.out.println("Palavra não está na árvore.");
        System.out.println("----");
        patricia.showNodesByLevel();
        System.out.println("----");
    }
}