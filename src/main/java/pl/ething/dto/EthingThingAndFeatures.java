/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.dto;

import java.util.List;
import pl.ething.model.EthingFeature;
import pl.ething.model.EthingThing;

/**
 *
 * @author Koksik
 */
public class EthingThingAndFeatures {
    
    private EthingThing ethingThing;
    private List<EthingFeature> ethingFeatures;

    public EthingThing getEthingThing() {
        return ethingThing;
    }

    public void setEthingThing(EthingThing ethingThing) {
        this.ethingThing = ethingThing;
    }

    public List<EthingFeature> getEthingFeatures() {
        return ethingFeatures;
    }

    public void setEthingFeatures(List<EthingFeature> ethingFeatures) {
        this.ethingFeatures = ethingFeatures;
    }
    
}
