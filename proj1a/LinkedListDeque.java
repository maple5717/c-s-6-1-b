// import java.util.Formatter;

public class LinkedListDeque<T> {
    private DeqNode sentinel;
    private DeqNode first;
    private DeqNode last;
    private int size;

    private class DeqNode {
        private T item;
        private DeqNode prev;
        private  DeqNode next;

        DeqNode(T i, DeqNode p, DeqNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    LinkedListDeque() {
        sentinel = new DeqNode(null, null, null);
        first = sentinel;
        last = sentinel;
        size = 0;
    }

    LinkedListDeque(LinkedListDeque other) {
        sentinel = new DeqNode(null, null, null);
        first = sentinel;
        last = sentinel;
        size = 0;

        if (other == null) {
            return;
        }

        DeqNode p_other = other.first;
        DeqNode p_this = sentinel;

        while (p_other != other.last.next && other.size > 0) {
            p_this.next = new DeqNode(p_other.item, p_this, null);
            p_this = p_this.next;
            p_other = p_other.next;
            size += 1;
        }
        p_this.next = sentinel;
        sentinel.prev = p_this;

        first = sentinel.next;
        last = sentinel.prev;
    }



    public void addLast(T x) {
        last.next = new DeqNode(x, last, sentinel);
        last = last.next; 
        sentinel.prev = last;

        if (size == 0){
            first = last;
        }
        size += 1;
    }
    public void addFirst(T x) {
        sentinel.next = new DeqNode(x, sentinel, first);
        first.prev = sentinel.next;
        first = first.prev; 
        

        if (size == 0){
            last = first;
        }
        size += 1;
    }

    public T removeFirst() {
        if (size == 0){
            return null;
        }
        DeqNode p = first;
        T ret = p.item;
        first = p.next;

        sentinel.next = first;
        first.prev = sentinel;

        size -= 1;
        return ret;

    }

    public T removeLast() {
        if (size == 0){
            return null;
        }
        DeqNode p = last;
        T ret = p.item;
        last = p.prev;

        sentinel.prev = last;
        last.next = sentinel;

        size -= 1;
        return ret;
    }

    public T get(int idx) {
        DeqNode p = first;
        for(int i = 0; i < idx; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int idx) {
//        if (idx == 0){
//            return first.item;
//        }
//        LinkedListDeque copy = new LinkedListDeque(this);
//        copy.removeFirst();
//        copy.getRecursive(idx-1);

            DeqNode p = get_node(first, idx);
            return p.item;
    }

    private DeqNode get_node(DeqNode p, int idx) {
        if (idx == 0){
            return p;
        }
        return get_node(p.next, idx - 1);
    }



    private T getFirst() {
        return first.item;
    }


    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return size;
    }
    private String get_str() {
        DeqNode p = first; 
        String out = "[";

        if (size == 0) {
            return "[]";
        }

        out += p.item;
        p = p.next;
        while (p.item != null) {
            out += (", " +  p.item.toString());
            p = p.next;
        }

        return out + "]";
    }

    public void printDeque() {
        DeqNode p = first;
        String out = "";

        if (size == 0) {
            System.out.println(out);
            return;
        }

        out += p.item;
        p = p.next;
        while (p.item != null) {
            out += (" " +  p.item.toString());
            p = p.next;
        }

        System.out.println(out);
    }





//    private static void main(String[] args) {
//        LinkedListDeque<Integer> d1 = new LinkedListDeque<Integer>();
//        System.out.println(d1.isEmpty());
//        System.out.println(d1.size());
//        d1.addLast(3);
//        d1.addLast(2);
//        d1.addLast(1);
//        d1.addFirst(1);
//        // 1 3 2 1 5
//        d1.addLast(5);
//
//        System.out.println("first: " + d1.get(4));
////        d1.removeLast();
//
//        System.out.println(d1.isEmpty());
//        System.out.println(d1.size());
//        System.out.println(d1.get_str());
//        LinkedListDeque d2 = new LinkedListDeque(d1);
//        System.out.println(d2.get_str());
//        d2.printDeque();
//
//
//         System.out.println(d1.getRecursive(4));
//    }

}
