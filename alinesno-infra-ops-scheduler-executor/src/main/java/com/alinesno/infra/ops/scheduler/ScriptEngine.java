package com.alinesno.infra.ops.scheduler;

import java.util.Map;

import org.codehaus.groovy.control.CompilerConfiguration;

public interface ScriptEngine {

    <T> T invoke(String scriptName, Map<String, Object> scriptParams);

    void setCompilerConfiguration(CompilerConfiguration cc);

}