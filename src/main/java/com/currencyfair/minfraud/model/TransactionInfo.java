package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 * Value object encapsulating details on the transaction.
 */
public class TransactionInfo implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private String txnId;
    private BigDecimal orderAmount;
    private Currency orderCurrency;
    private String shopId;
    private TransactionType txnType;

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, "txnID", txnId);
        ValueUtils.add(params, "order_amount", orderAmount);
        ValueUtils.add(params, "order_currency", orderCurrency);
        ValueUtils.add(params, "shopID", shopId);
        ValueUtils.add(params, "txn_type", txnType);
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Currency getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(Currency orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public TransactionType getTxnType() {
        return txnType;
    }

    public void setTxnType(TransactionType txnType) {
        this.txnType = txnType;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(77, 79);
        b.append(txnId);
        b.append(orderAmount);
        b.append(orderCurrency);
        b.append(shopId);
        b.append(txnType);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof TransactionInfo)) {
            return false;
        }
        TransactionInfo rhs = (TransactionInfo) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(txnId, rhs.txnId);
        b.append(orderAmount, rhs.orderAmount);
        b.append(orderCurrency, rhs.orderCurrency);
        b.append(shopId, rhs.shopId);
        b.append(txnType, rhs.txnType);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? I take umbrage.", x);
        }
    }
}
