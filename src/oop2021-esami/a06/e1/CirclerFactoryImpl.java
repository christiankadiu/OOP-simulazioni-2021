package a06.e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CirclerFactoryImpl implements CirclerFactory {

    @Override
    public <T> Circler<T> leftToRight() {
        return new Circler<T>() {
            List<T> lista;
            Iterator<T> it;

            @Override
            public void setSource(List<T> elements) {
                lista = new ArrayList<>(elements);
                it = lista.iterator();
            }

            @Override
            public T produceOne() {
                if (it.hasNext()) {
                    return it.next();
                }
                setSource(lista);
                return it.next();
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    res.add(produceOne());
                }
                return res;
            }

        };
    }

    @Override
    public <T> Circler<T> alternate() {
        return new Circler<T>() {
            List<T> lista;
            Iterator<T> it;

            @Override
            public void setSource(List<T> elements) {
                lista = new ArrayList<>(elements);
                it = lista.iterator();
            }

            @Override
            public T produceOne() {
                if (it.hasNext()) {
                    return it.next();
                }
                Collections.reverse(lista);
                setSource(lista);
                return it.next();
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    res.add(produceOne());
                }
                return res;
            }

        };
    }

    @Override
    public <T> Circler<T> stayToLast() {
        return new Circler<T>() {

            List<T> lista;
            Iterator<T> it;

            @Override
            public void setSource(List<T> elements) {
                lista = new ArrayList<>(elements);
                it = lista.iterator();
            }

            @Override
            public T produceOne() {
                if (it.hasNext()) {
                    return it.next();
                }
                return lista.getLast();
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    res.add(produceOne());
                }
                return res;
            }

        };
    }

    @Override
    public <T> Circler<T> leftToRightSkipOne() {
        return new Circler<T>() {
            List<T> lista;
            Iterator<T> it;
            boolean can = true;
            int current = 0;

            @Override
            public void setSource(List<T> elements) {
                lista = new ArrayList<>(elements);
                it = lista.iterator();
            }

            @Override
            public T produceOne() {
                T t;
                if (it.hasNext() && can) {
                    can = false;
                    return it.next();
                }
                setSource(lista);
                can = true;
                return it.next();
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    res.add(produceOne());
                }
                return res;
            }
        };
    }

    @Override
    public <T> Circler<T> alternateSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alternateSkipOne'");
    }

    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stayToLastSkipOne'");
    }

}
