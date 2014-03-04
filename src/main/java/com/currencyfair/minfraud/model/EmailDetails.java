package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating the result of email checks.
 * @since 1.0.0
 */
public class EmailDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private boolean freeMail;
    private Boolean carderEmail;

    /**
     * Construct a new <tt>EmailDetails</tt> and populate its fields with
     * values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>EmailDetails</tt>.
     */
    public static EmailDetails extract(Map<String, String> values) {
        EmailDetails e = new EmailDetails();
        e.freeMail = ValueUtils.extractBoolean(values.get("freeMail"));
        e.carderEmail = ValueUtils.extractBooleanOrNull(values.get("carderEmail"));
        return e;
    }

    public boolean isFreeMail() {
        return freeMail;
    }

    public void setFreeMail(boolean freeMail) {
        this.freeMail = freeMail;
    }

    public Boolean isCarderEmail() {
        return carderEmail;
    }

    public void setCarderEmail(Boolean carderEmail) {
        this.carderEmail = carderEmail;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(27, 33);
        b.append(freeMail);
        b.append(carderEmail);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof EmailDetails)) {
            return false;
        }
        EmailDetails rhs = (EmailDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(freeMail, rhs.freeMail);
        b.append(carderEmail, rhs.carderEmail);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? You lie.", x);
        }
    }
}
