package com.currencyfair.minfraud;

import com.currencyfair.minfraud.model.RiskScoreRequest;
import com.currencyfair.minfraud.model.RiskScoreResponse;

import java.io.IOException;
import java.net.URI;

/**
 * Interface for interacting with the MinFraud API.
 */
public interface MinFraud {
    /**
     * The default URL of the MinFraud HTTP endpoint.
     */
    URI DEFAULT_URL = URI.create("https://minfraud.maxmind.com/app/ccv2r");

    /**
     * Calculate the risk score for a given request.
     * @param request The request object to calculate the risk score for.
     * @return The response object containing the details of the risk
     * assessment.
     * @throws IllegalArgumentException If the <tt>request</tt> is not
     * well formed, such as missing required fields.
     * @throws IOException If there is a problem communicating with the
     * remote MinFraud service endpoint.
     */
    RiskScoreResponse calculateRisk(RiskScoreRequest request) throws IOException;
}
