package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final List<T> myList = new ArrayList<>();

    public IterableWithPolicyImpl(T[] elements){
        for(int i=0; i<elements.length; i++){
        myList.add(elements[i]);
    }

}

    @Override
    public Iterator<T> iterator() {
        InnerIteratorImpl iii = new InnerIteratorImpl();
        return iii;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }
    
    private class InnerIteratorImpl implements Iterator<T>{
        private int count=0;

        @Override
        public boolean hasNext() {
            return count < myList.size();
        }

        
        @Override
        public T next() {
            return myList.get(count++);
        }

    }

}
