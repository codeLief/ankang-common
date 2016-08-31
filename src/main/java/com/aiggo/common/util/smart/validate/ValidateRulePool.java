package com.aiggo.common.util.smart.validate;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.aiggo.common.util.smart.validate.match.AbstractMatchValidate;
import com.aiggo.common.util.smart.validate.match.MatchMaxCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.MatchMaxLengthValidate;
import com.aiggo.common.util.smart.validate.match.MatchMaxValueValidate;
import com.aiggo.common.util.smart.validate.match.MatchMinCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.MatchMinLengthValidate;
import com.aiggo.common.util.smart.validate.match.MatchMinValueValidate;
import com.aiggo.common.util.smart.validate.match.MatchNotNullValidate;
import com.aiggo.common.util.smart.validate.match.MatchRangeCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.MatchRangeLengthValidate;
import com.aiggo.common.util.smart.validate.match.MatchRangeValueValidate;
import com.aiggo.common.util.smart.validate.match.MatchRegexpValidate;
import com.aiggo.common.util.smart.validate.match.rule.MaxCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.rule.MaxLengthValidate;
import com.aiggo.common.util.smart.validate.match.rule.MaxValueValidate;
import com.aiggo.common.util.smart.validate.match.rule.MinCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.rule.MinLengthValidate;
import com.aiggo.common.util.smart.validate.match.rule.MinValueValidate;
import com.aiggo.common.util.smart.validate.match.rule.NotNullValidate;
import com.aiggo.common.util.smart.validate.match.rule.RangeCollectionsSizeValidate;
import com.aiggo.common.util.smart.validate.match.rule.RangeLengthValidate;
import com.aiggo.common.util.smart.validate.match.rule.RangeValueValidate;
import com.aiggo.common.util.smart.validate.match.rule.RegexpValidate;


public class ValidateRulePool {

	private static final Map<Class<? extends Annotation>, AbstractMatchValidate<? extends Annotation>> matchValidatePool = new HashMap<>();

	static {
		
		mount(MaxLengthValidate.class, new MatchMaxLengthValidate());
		mount(MaxValueValidate.class, new MatchMaxValueValidate());
		mount(MinLengthValidate.class, new MatchMinLengthValidate());
		mount(MinValueValidate.class, new MatchMinValueValidate());
		mount(NotNullValidate.class, new MatchNotNullValidate());
		mount(RangeLengthValidate.class, new MatchRangeLengthValidate());
		mount(RangeValueValidate.class, new MatchRangeValueValidate());
		mount(RegexpValidate.class, new MatchRegexpValidate());
		mount(MaxCollectionsSizeValidate.class, new MatchMaxCollectionsSizeValidate());
		mount(MinCollectionsSizeValidate.class, new MatchMinCollectionsSizeValidate());
		mount(RangeCollectionsSizeValidate.class, new MatchRangeCollectionsSizeValidate());

	}
	private ValidateRulePool() {
		
	}
	
	public static void mount(Class<? extends Annotation> alias, AbstractMatchValidate<? extends Annotation> handler) {
		
		matchValidatePool.put(alias, handler);
		
	}
	
	public static AbstractMatchValidate<? extends Annotation> get(Class<? extends Annotation> alias) {

		return matchValidatePool.get(alias);
	}
}
