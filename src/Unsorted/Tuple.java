package Unsorted;

public class Tuple<E> {

    private E label,password;

    public Tuple(E label, E password) {
        this.label = label;
        this.password = password;
    }

    public E getLabel() {
        return this.label;
    }

    public E getPassword() {
        return this.password;
    }

    public void setLabel(E first) {
        this.label = first;
    }

    public void setPassword(E second) {
        this.password = second;
    }
    
    @Override
    public String toString() {
    	return label.toString() + ", " + password.toString();
    }
}
