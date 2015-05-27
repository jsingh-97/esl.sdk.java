package com.silanis.esl.sdk.examples;

import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExampleTest {
    @Test
    public void verifyResult() {
        SignatureManipulationExample example = new SignatureManipulationExample(Props.get());
        example.run();

        // Test if all signatures are added properly
        Map<String,Signature> signatureMap = convertListToMap(example.addedSignatures);

        assertThat("Signature 1 was not set correctly", signatureMap.containsKey(example.email1), is(true));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(example.email2), is(true));
        assertThat("Signature 3 was not set correctly", signatureMap.containsKey(example.email3), is(true));

        // Test if signature1 is deleted properly
        signatureMap = convertListToMap(example.deletedSignatures);

        assertThat("Signature 1 was not deleted correctly", signatureMap.containsKey(example.email1), is(false));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(example.email2), is(true));
        assertThat("Signature 3 was not set correctly", signatureMap.containsKey(example.email3), is(true));

        // Test if signature3 is updated properly and is assigned to signer1
        signatureMap = convertListToMap(example.modifiedSignatures);

        assertThat("Signature 1 was not set correctly", signatureMap.containsKey(example.email1), is(true));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(example.email2), is(true));
        assertThat("Signature 3 was not modified correctly", signatureMap.containsKey(example.email3), is(false));

        // Test if the signatures were updated with the new list of signatures
        signatureMap = convertListToMap(example.updatedSignatures);

        assertThat("Signature 1 was not updated correctly", signatureMap.containsKey(example.email1), is(false));
        assertThat("Signature 2 was not updated correctly", signatureMap.containsKey(example.email2), is(true));
        assertThat("Signature 3 was not updated correctly", signatureMap.containsKey(example.email3), is(true));
        assertThat("Signature 2 was not updated with the name field", Iterables.get(signatureMap.get(example.email2).getFields(), 0).getStyle(), is(FieldStyle.BOUND_NAME));
    }

    private Map<String, Signature> convertListToMap(Collection<Signature> signatures){
        Map<String,Signature> signatureMap = new HashMap<String, Signature>();
        for (Signature signature : signatures){
            signatureMap.put(signature.getSignerEmail(), signature);
        }

        return signatureMap;
    }
}
