/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ipplanmanager.service;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;

public interface RegleRecommandation {
    Recommandation analyser(VLAN vlan);  
}
