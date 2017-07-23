import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class OneDriverStation extends OneSwing {
	
	public static final int SPACING = 10;
	
	
	public OneDriverStation() {
		JPanel all = construct(
				new JPanel(),
				Colors.WINDOW_BACKGROUND,
				setDimensionsStrict(1054, 207),
				initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
				Box.createHorizontalStrut(SPACING),
				construct(
						new JPanel(),
						Colors.WINDOW_BACKGROUND,
						setDimensionsStrict(36, 147),
						initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_DASHBOARD),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_STETHOSCOPE),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_WRENCH),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_USB),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									)
						),
				Box.createHorizontalStrut(SPACING),
				construct(
						new JPanel(),
						Colors.WINDOW_BACKGROUND,
						initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
						Box.createVerticalStrut(SPACING),
						construct(
								new RoundedRect(10),
								Colors.WIDGET_BACKGROUND,
								setDimensionsStrict(395, 187),
								initializeWith((RoundedRect rect) -> new BoxLayout(rect, BoxLayout.PAGE_AXIS)),
								Box.createVerticalStrut(SPACING),
								construct(
										new JPanel(),
										Colors.WIDGET_BACKGROUND,
										setDimensionsStrict(395, 167),
										initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
										Box.createHorizontalStrut(SPACING),
										construct(
												new JPanel(),
												Colors.WIDGET_BACKGROUND,
												setDimensionsStrict(159, 167),
												initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
												construct(
														new JPanel(),
														Colors.WINDOW_BACKGROUND,
														setDimensionsStrict(159, 93),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														Box.createHorizontalStrut(1),
														construct(
																new JPanel(),
																Colors.WINDOW_BACKGROUND,
																setDimensionsStrict(157, 93),
																initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
																Box.createVerticalStrut(1),
																construct(
																		new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																		setDimensionsStrict(157, 22),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(SPACING),
																		construct(
																				new JLabel("Teleoperated"),
																				initializeWith((JLabel label) -> {
																					label.setFont(Fonts.regular(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																					})
																				),
																		Box.createHorizontalGlue()
																		),
																Box.createVerticalStrut(1),
																construct(
																		new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																		setDimensionsStrict(157, 22),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(SPACING),
																		construct(
																				new JLabel("Autonomous"),
																				initializeWith((JLabel label) -> {
																					label.setFont(Fonts.regular(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																					})
																				),
																		Box.createHorizontalGlue()
																		),
																Box.createVerticalStrut(1),
																construct(
																		new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																		setDimensionsStrict(157, 22),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(SPACING),
																		construct(
																				new JLabel("Practice"),
																				initializeWith((JLabel label) -> {
																					label.setFont(Fonts.regular(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																					})
																				),
																		Box.createHorizontalGlue()
																		),
																Box.createVerticalStrut(1),
																construct(
																		new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																		setDimensionsStrict(157, 22),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(SPACING),
																		construct(
																				new JLabel("Test"),
																				initializeWith((JLabel label) -> {
																					label.setFont(Fonts.regular(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																					})
																				),
																		Box.createHorizontalGlue()
																		),
																Box.createVerticalStrut(1)
																),
														Box.createHorizontalStrut(1)
														),
													Box.createVerticalStrut(15),
													construct(
															new JPanel(),
															Colors.WINDOW_BACKGROUND,
															setDimensionsStrict(159, 59),
															initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
															Box.createVerticalStrut(1),
															construct(
																	new JPanel(),
																	Colors.WINDOW_BACKGROUND,
																	setDimensionsStrict(159, 57),
																	initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																	Box.createHorizontalStrut(1),
																	construct(
																			new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																			setDimensionsStrict(78, 57),
																			new GridBagLayout(),
																			construct(
																					new JLabel(" Enable "),
																					initializeWith((JLabel label) -> {
																						label.setFont(Fonts.bold(16));
																						label.setForeground(Colors.ENABLE_BUTTON_UNSELECTED);
																						})
																					)
																			),
																	Box.createHorizontalStrut(1),
																	construct(
																			new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																			setDimensionsStrict(78, 57),
																			new GridBagLayout(),
																			construct(
																					new JLabel(" Disable "),
																					initializeWith((JLabel label) -> {
																						label.setFont(Fonts.bold(16));
																						label.setForeground(Colors.DISABLE_BUTTON_UNSELECTED);
																						})
																					)
																			),
																	Box.createHorizontalStrut(1)
																	),
															Box.createVerticalStrut(1)
															)
												),
										Box.createHorizontalStrut(SPACING),
										construct(
												new JPanel(),
												Colors.WINDOW_BACKGROUND,
												setDimensionsStrict(1, 167)
												),
										Box.createHorizontalStrut(SPACING),
										construct(
												new JPanel(),
												Colors.WIDGET_BACKGROUND,
												setDimensionsStrict(195, 167),
												initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
												construct(
														new JPanel(),
														Colors.WIDGET_BACKGROUND,
														setDimensionsStrict(195, 20),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														construct(
																new JLabel("Elapsed Time"),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.regular(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalGlue(),
														construct(
																new JLabel("00:00.0"),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.bold(20));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																)
														),
												Box.createVerticalStrut(SPACING * 5 / 2),
												construct(
														new JPanel(),
														Colors.WIDGET_BACKGROUND,
														setDimensionsStrict(195, 16),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														Box.createHorizontalGlue(),
														construct(
																new JLabel(Icons.FA_PLUG),
																initializeWith((JLabel label) -> {
																	label.setFont(Icons.FA(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalStrut(5),
														construct(
																new JLabel("PC Battery"),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.regular(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalStrut(SPACING),
														construct(
																new JPanel(),
																Colors.WINDOW_BACKGROUND,
																setDimensionsStrict(102, 14)
																)
														),
												Box.createVerticalStrut(SPACING / 2),
												construct(
														new JPanel(),
														Colors.WIDGET_BACKGROUND,
														setDimensionsStrict(195, 16),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														Box.createHorizontalGlue(),
														construct(
																new JLabel("PC CPU % "),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.regular(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalStrut(SPACING),
														construct(
																new JPanel(),
																Colors.WINDOW_BACKGROUND,
																setDimensionsStrict(102, 14)
																)
														),
												Box.createVerticalStrut(SPACING * 2),
												construct(
														new JPanel(),
														Colors.WIDGET_BACKGROUND,
														setDimensionsStrict(195, 24),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														Box.createHorizontalGlue(),
														construct(
																new JLabel("Window "),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.regular(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalStrut(10),
														construct(
																new JPanel(),
																Colors.WINDOW_BACKGROUND,
																setDimensionsStrict(102, 24),
																initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
																Box.createVerticalStrut(1),
																construct(
																		new JPanel(),
																		Colors.WINDOW_BACKGROUND,
																		setDimensionsStrict(102, 22),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(1),
																		construct(
																				new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																				setDimensionsStrict(50, 22),
																				construct(
																						new JLabel(Icons.FA_REPLY),
																						initializeWith((JLabel label) -> {
																							label.setFont(Icons.FA(12));
																							label.setForeground(Colors.WIDGET_FOREGROUND);
																						})
																						)
																				),
																		Box.createHorizontalStrut(1),
																		construct(
																				new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
																				setDimensionsStrict(49, 22),
																				construct(
																						new JLabel(Icons.FA_EXPAND),
																						initializeWith((JLabel label) -> {
																							label.setFont(Icons.FA(12));
																							label.setForeground(Colors.WIDGET_FOREGROUND);
																						})
																						)
																				),
																		Box.createHorizontalStrut(1)
																		),
																Box.createVerticalStrut(1)
																)
														),
												Box.createVerticalStrut(SPACING * 2),
												construct(
														new JPanel(),
														Colors.WIDGET_BACKGROUND,
														setDimensionsStrict(195, 24),
														initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
														Box.createHorizontalGlue(),
														construct(
																new JLabel("Team Station"),
																initializeWith((JLabel label) -> {
																	label.setFont(Fonts.regular(12));
																	label.setForeground(Colors.WIDGET_FOREGROUND);
																})
																),
														Box.createHorizontalStrut(10),
														construct(
																new JPanel(),
																Colors.WINDOW_BACKGROUND,
																setDimensionsStrict(102, 19),
																initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
																Box.createVerticalStrut(1),
																construct(
																		new JPanel(),
																		Colors.WIDGET_BACKGROUND,
																		setDimensionsStrict(100, 17),
																		initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
																		Box.createHorizontalStrut(SPACING/2),
																		construct(
																				new JLabel("Red 1"),
																				initializeWith((JLabel label) -> {
																					label.setFont(Fonts.regular(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																				})
																				),
																		Box.createHorizontalGlue(),
																		construct(
																				new JLabel(Icons.FA_CHEVRON_DOWN),
																				initializeWith((JLabel label) -> {
																					label.setFont(Icons.FA(12));
																					label.setForeground(Colors.WIDGET_FOREGROUND);
																				})
																				),
																		Box.createHorizontalStrut(SPACING/2)
																		)
																),
																Box.createVerticalStrut(1)
														)
												),
										Box.createHorizontalStrut(SPACING)
										),
								Box.createVerticalStrut(SPACING)
								),
						Box.createVerticalStrut(SPACING)
						),
				Box.createHorizontalStrut(SPACING),
				construct(
						new JPanel(),
						Colors.WINDOW_BACKGROUND,
						setDimensionsStrict(156, 187),
						initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
						construct(new JPanel(), Colors.WINDOW_BACKGROUND,
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								construct(new JLabel("Team # "), initializeWith((JLabel label) -> {
									label.setFont(Fonts.bold(16));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								})), Box.createHorizontalGlue(),
								construct(new JLabel("1072"), initializeWith((JLabel label) -> {
									label.setFont(Fonts.bold(16));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								}))),
						Box.createVerticalStrut(SPACING),
						construct(new JPanel(), Colors.WINDOW_BACKGROUND, setDimensionsStrict(156, 40),
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								new JImage("images/batteryicon.png"), Box.createHorizontalGlue(),
								construct(new JLabel("--.--"), initializeWith((JLabel label) -> {
									label.setFont(Fonts.bold(18));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								}))),
						Box.createVerticalStrut(SPACING),
						construct(new JPanel(), Colors.WINDOW_BACKGROUND,
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								Box.createHorizontalGlue(),
								construct(new JLabel("Communications"), initializeWith((JLabel label) -> {
									label.setFont(Fonts.regular(12));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								})), Box.createHorizontalStrut(SPACING),
								construct(new JPanel(), Colors.INDICATOR_ERROR, setDimensionsStrict(28, 13))),
						Box.createVerticalStrut(3),
						construct(new JPanel(), Colors.WINDOW_BACKGROUND,
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								Box.createHorizontalGlue(),
								construct(new JLabel("Robot Code"), initializeWith((JLabel label) -> {
									label.setFont(Fonts.regular(12));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								})), Box.createHorizontalStrut(SPACING),
								construct(new JPanel(), Colors.INDICATOR_ERROR, setDimensionsStrict(28, 13))),
						Box.createVerticalStrut(3),
						construct(new JPanel(), Colors.WINDOW_BACKGROUND,
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								Box.createHorizontalGlue(),
								construct(new JLabel("Joysticks"), initializeWith((JLabel label) -> {
									label.setFont(Fonts.regular(12));
									label.setForeground(Colors.WIDGET_FOREGROUND);
								})), Box.createHorizontalStrut(SPACING),
								construct(new JPanel(), Colors.INDICATOR_ERROR, setDimensionsStrict(28, 13))),
						Box.createVerticalGlue(),
						construct(
								new JPanel(),
								Colors.WINDOW_BACKGROUND,
								initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.LINE_AXIS)),
								construct(
										new JLabel(
												"<html><center><font face=\"ubuntu\" color=\"white\" size = \"4\"><b>No Robot Communications</b></font></center></html>"),
										setDimensionsStrict(156, 40)))),
				Box.createHorizontalStrut(SPACING),
				construct(new RoundedRect(10), Colors.WIDGET_BACKGROUND, initializeWith((RoundedRect panel) -> {
					panel.setMinimumSize(new Dimension(395, 187));
					panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 187));
				})),
				Box.createHorizontalStrut(SPACING),
				construct(
						new JPanel(),
						Colors.WINDOW_BACKGROUND,
						setDimensionsStrict(36, 193),
						initializeWith((JPanel panel) -> new BoxLayout(panel, BoxLayout.PAGE_AXIS)),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_ENVELOPE),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_AREA_CHART),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_INFO),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(SPACING),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_GEAR),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									),
						Box.createVerticalStrut(1),
						construct(
								new ButtonWidget(Colors.WIDGET_BACKGROUND, Colors.WINDOW_BACKGROUND),
								setDimensionsStrict(36, 36),
								new GridBagLayout(),
								construct(
										new JLabel(Icons.FA_KEYBOARD_O),
										initializeWith((JLabel label) -> {
											label.setFont(Icons.FA(16));
											label.setForeground(Colors.WIDGET_FOREGROUND);
										})
										)
									)
								),
						Box.createHorizontalStrut(SPACING)
						);
		add(all);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1054, 227));
		setMaximumSize(new Dimension(1054, Integer.MAX_VALUE));
	}
	
	public static void main(String[] args) {
		new OneDriverStation();
	}
}
