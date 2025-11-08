package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final List<T> list = new ArrayList<>();
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements){
        for(int i=0; i<elements.length; i++){
        list.add(elements[i]);
    }
}

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter){
        setIterationPolicy(filter);
        for(int i=0; i<elements.length; i++){
        list.add(elements[i]);
    }
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter=filter;
    }
    
    private class InnerIteratorImpl implements Iterator<T>{
        private int count=0;

        @Override
        public boolean hasNext() {
            while(count < list.size()){
                var elem = list.get(count);
                if(filter == null || filter.test(elem)){
                    return true;
                }
                else{
                    count++;
                }
            }
            return false;
        }

        
        @Override
        public T next() {
            if(hasNext()){
                return list.get(count++);
            }
            throw new NoSuchElementException();
        }

    }

}
