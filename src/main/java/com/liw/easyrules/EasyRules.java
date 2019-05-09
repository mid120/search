package com.liw.easyrules;


import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class EasyRules  {


    public  void demo() throws FileNotFoundException {
        Person person = new Person("Tom", 14);
        Facts fact = new Facts();
        fact.put("person", person);
    MVELRule alcoholRule = MVELRuleFactory.createRuleFrom(new File(getClass().getClassLoader().getResource("alcohol-rule.yml").getFile()));

    // create a rule set
    Rules rules = new Rules();
    rules.register(alcoholRule);

    //create a default rules engine and fire rules on known facts
    RulesEngine rulesEngine = new DefaultRulesEngine();

        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, fact);

    }
    public EasyRules() {
    }




}
