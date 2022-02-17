package com.epam.autotasks.collections;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RangedOpsIntegerSet extends AbstractSet<Integer> {

    private Integer numbers[];


    public RangedOpsIntegerSet() {
        numbers = new Integer[0];
    }

    public boolean add(int fromInclusive, int toExclusive) {
        boolean isInserted = false;

        for (int i = fromInclusive; i < toExclusive; i++) {
            boolean result = add(i);
            if (result && !isInserted) isInserted = true;
        }
        return isInserted;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        boolean isRemoved = false;

        for (int i = fromInclusive; i < toExclusive; i++) {
            boolean result = remove(i);
            if (result && !isRemoved) isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public boolean add(final Integer integer) {
        if (elementExist(integer)) {
            return false;
        }
        Integer[] newArray = Arrays.copyOf(numbers, numbers.length + 1);
        newArray[numbers.length] = integer;
        Arrays.sort(newArray);
        numbers = Arrays.copyOf(newArray, newArray.length);

        return true;
    }

    public boolean elementExist(Integer number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals(number)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean remove(final Object o) {

        Integer item = (Integer) o;
        if(!elementExist(item)) return false;

        int pos = getPosition(item);
        for (int i = pos; i < numbers.length-1 ; i++) {
            numbers[i]= numbers[i+1];
        }
        numbers = Arrays.copyOf(numbers, numbers.length-1);

        return true;
    }
    private int getPosition (Integer number){
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i].equals( number)){
                return i;
            }
        }
        return -1;
    }

    public boolean determinateValueRange(int value, int fromInclusive, int toExclusive) {
        return value >= fromInclusive && value <= toExclusive;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }

    @Override
    public int size() {
        return numbers.length;
    }

    class IntegerIterator implements Iterator<Integer> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return numbers.length > i;
        }

        @Override
        public Integer next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return numbers[i++];
        }
    }

    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();
        set.add(11, 15);
        set.add(7);
        for (Integer num : set) {
            System.out.print(num + " ");
        }
        System.out.println();

//        RangedOpsIntegerSet set1 = new RangedOpsIntegerSet();
//        set1.add(1, 10);
//       // set1.add(5);
//        for (Integer num : set1) {
//            System.out.print(num + " ");
//        }
        System.out.println();

    }
}
