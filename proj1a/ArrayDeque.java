public class ArrayDeque<T> {
    private int size;
    private int len;
    private int nextLast;
    private int nextFirst;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        len = 8;
        nextFirst = 3;
        nextLast = 4;
        items = (T []) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        len = size * 2;

        if (len < 8) {
            len = 8;
        }

        items = (T []) new Object[len];
        int bias = len / 2;
        for (int i = 0; i < size; i++) {
            items[bias + i] = (T) other.get(i);
        }

        nextFirst = bias - 1;
        nextLast = bias + size + 1;

    }

    public void addFirst(T x) {
        items[Math.floorMod(nextFirst, len)] = x;
        nextFirst -= 1;
        size += 1;
        expand();
    }

    public void addLast(T x) {
//        System.out.println(Math.floorMod(nextLast, len));
        items[Math.floorMod(nextLast, len)] = x;
        nextLast += 1;
        size += 1;
        expand();
    }


    private void expand() {
        int nextLast_pos = Math.floorMod(nextLast, len);
        int nextFirst_pos = Math.floorMod(nextFirst, len);
        if (nextLast_pos != nextFirst_pos) {
            return;
        }

        resize(len * 2, len / 2);
//        int len_new = len * 2;
//        int item_num = nextLast - nextFirst - 1;
//        int bias = len / 2;
//        T[] items_new = (T []) new Object[len_new];
//
//        for (int i = nextFirst + 1; i <= nextLast - 1; i++){
//            int j = i - nextFirst - 1 + bias;
//            items_new[j] = items[Math.floorMod(i, len)];
//        }
//
//        items = items_new;
//        nextFirst = bias - 1;
//        nextLast = bias + item_num;
//        len = len_new;

    }

    private void resize(int len_new, int bias) {
        int item_num = nextLast - nextFirst - 1;
        T[] items_new = (T []) new Object[len_new];

        for (int i = nextFirst + 1; i <= nextLast - 1; i++) {
            int j = i - nextFirst - 1 + bias;
            items_new[j] = items[Math.floorMod(i, len)];
        }

        items = items_new;
        nextFirst = bias - 1;
        nextLast = bias + item_num;
        len = len_new;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = get(0);
        nextFirst += 1;
        size -= 1;
        shrink();
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = get(size - 1);
        nextLast -= 1;
        size -= 1;
        shrink();
        return ret;
    }


    private void shrink() {
        int item_num = (nextLast - nextFirst - 1);
        if (item_num * 4 >= len) {
            return;
        }

        if (len <= 16) {
            return;
        }

        resize(item_num * 2, item_num / 2);

    }

    public T get(int idx) {
        int pos = Math.floorMod(nextFirst + 1 + idx, len);
        return items[pos];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void printDeque() {
        String str = "";
        for (int i = 0; i <= len - 1; i++) {
            if (i == Math.floorMod(nextFirst, len)) {
                str += " {" + items[i] + "} ";
            } else if (i == Math.floorMod(nextLast, len)) {
                str += " [" + items[i] + "] ";
            } else {
                str += " " + items[i] + " ";
            }
        }

        System.out.println(str);
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> d1 = new ArrayDeque<Integer>();
//
//        for (int i = 0; i <= 20; i++) {
//            d1.addLast(i);
////            d1.printDeque();
//        }
//
//        for (int i = 0; i <= 18; i++) {
//            int a = (int) d1.removeLast();
//            System.out.println(a);
////            d1.printDeque();
//        }
//        System.out.println(d1.size());
//
//        ArrayDeque<Integer> d2 = new ArrayDeque<Integer>(d1);
//        d2.printDeque();
//
//
//
//
//    }


}
