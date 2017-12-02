
public class LeakyString {
    private final String referenceString;
    private final String substring;
    static String interned;

    /**
     * LeakyString constructor.
     * Creates a string from 0 to 999 and also a substring of it
     * consisting of one letter. The class operates only on the
     * {@link #substring} which holds the reference to the {@link #referenceString}
     */
    public LeakyString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append(String.valueOf(i));
        }
        this.referenceString = builder.toString();
        this.substring = referenceString.substring(0, 1);
    }

    /**
     * Creates an even worse leak by interning the substring holding
     * the reference to the {@link #referenceString}. Until the release of
     * Java 8, interned strings have been kept in PermGen space (special permanent
     * generation space holding the metadata describing user classes - classes that are
     * not a part of Java language), however this has been changed in Java 8 - now they
     * are kept in so called Metaspace, which won't cause the memory leak when interning
     * strings anymore.
     */
    public void leakEvenMore(){
        interned = substring.intern();
    }
}
