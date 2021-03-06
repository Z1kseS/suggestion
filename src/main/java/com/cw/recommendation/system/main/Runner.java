package com.cw.recommendation.system.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cw.recommendation.system.config.AppConfig;
import com.cw.recommendation.system.entity.Module;
import com.cw.recommendation.system.entity.Subject;
import com.cw.recommendation.system.service.application.CommonRecommendationService;
import com.cw.recommendation.system.service.application.SubjectSelectorService;

public class Runner {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		CommonRecommendationService recommendationService = ctx.getBean(CommonRecommendationService.class);
		SubjectSelectorService minYears = ctx.getBean("MinYears", SubjectSelectorService.class);
		SubjectSelectorService minSubjects = ctx.getBean("MinSubjects", SubjectSelectorService.class);

		System.out.println(recommendationService.recommendSubjects(1L, 1L, minSubjects));
		System.out.println(recommendationService.recommendSubjects(1L, 1L, minYears));

		for (Subject subject : recommendationService.recommendSubjects(1L, 1L, minYears)) {
			System.out.println(subject.getName() + ": ");

			for (Module module : subject.getSubjectModules())
				System.out.println("  - " + module.getName());
		}
		ctx.close();
	}

}
