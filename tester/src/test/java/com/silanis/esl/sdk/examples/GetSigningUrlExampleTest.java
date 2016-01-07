package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.internal.HttpRequestUtil.getUrlContent;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 1/23/15.
 */
public class GetSigningUrlExampleTest {

    @Test
    public void verifyResult() {
        GetSigningUrlExample example = new GetSigningUrlExample(Props.get());
        example.run();

        assertThat("Signing URL for Signer 1 is not returned.", example.signingUrlForSigner1, not(isEmptyOrNullString()));
        String stringResponse1 = getUrlContent(example.signingUrlForSigner1);
        assertThat(stringResponse1, containsString("Electronic Disclosures and Signatures Consent"));

        assertThat("Signing URL for Signer 2 is not returned.", example.signingUrlForSigner2, not(isEmptyOrNullString()));
        String stringResponse2 = getUrlContent(example.signingUrlForSigner2);
        assertThat(stringResponse2, containsString("Electronic Disclosures and Signatures Consent"));
    }
}
