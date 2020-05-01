package com.desenvolvimento.pibetting.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.desenvolvimento.pibetting.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.desenvolvimento.pibetting.thymeleaf.processor.MessageElementTagProcessor;

public class PibettingDialect extends AbstractProcessorDialect {

	public PibettingDialect() {
		super("Pibetting Dialect", "pibetting", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}
	
	
}
