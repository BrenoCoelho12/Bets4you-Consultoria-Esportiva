package com.desenvolvimento.bets4you.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.desenvolvimento.bets4you.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.desenvolvimento.bets4you.thymeleaf.processor.MessageElementTagProcessor;

public class Bets4youDialect extends AbstractProcessorDialect {

	public Bets4youDialect() {
		super("Bets4you Dialect", "bets4you", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}
	
	
}
