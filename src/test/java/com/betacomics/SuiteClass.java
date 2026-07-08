package com.betacomics;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacomics.actionFigure.ActionFigureTest;
import com.betacomics.boardGame.BoardGameTest;
import com.betacomics.comic.ComicTest;
import com.betacomics.delete.DeleteTest;
import com.betacomics.product.ProductTest;

@Suite
@SelectClasses({
	ComicTest.class,
	ActionFigureTest.class,
	BoardGameTest.class,
	ProductTest.class,
	DeleteTest.class
})

public class SuiteClass {

}
