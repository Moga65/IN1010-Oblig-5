public class Subsekvens {
    public final String subsekvens;
    public int antall_forekomster = 1;

    public Subsekvens(String subsekvens) {
        this.subsekvens = subsekvens;
    }

    public int hentforekomster() {
        return antall_forekomster;
    }

    public String hentSubsekvens() {
        return subsekvens;
    }

    public void settnullForekomst() {
        antall_forekomster = 0;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "og " + antall_forekomster + ")";
    }
}
