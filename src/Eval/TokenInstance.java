package Eval;

public class TokenInstance {
    public String text;
    public TokenType type;

    public TokenInstance(TokenType type, String text) {
        this.text = text;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TokenInstance)) return false;
        TokenInstance ti = (TokenInstance) o;
        return ti.text.equals(this.text) && ti.type.equals(this.type);
    }

    @Override
    public String toString() {
        return this.type + "(\"" + this.text + "\")";
    }
}
