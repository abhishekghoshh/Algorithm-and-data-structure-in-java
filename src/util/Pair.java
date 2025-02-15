package util;

public class Pair<T1, T2> {
    public T1 first;
    public T2 second;
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    

    /**
     * @return T1 return the first
     */
    public T1 getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(T1 first) {
        this.first = first;
    }

    /**
     * @return T1 return the second
     */
    public T2 getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(T2 second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return "Pair [first=" + first + ", second=" + second + "]";
    }

}
