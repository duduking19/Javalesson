package com.example.question4;

import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * 第4题主程序。
 * 该程序使用 JavaFX 创建图形界面，
 * 点击按钮后会随机决定绘制直线、矩形或椭圆中的一种。
 */
public class Question4App extends Application {
    private static final double DRAW_WIDTH = 700;
    private static final double DRAW_HEIGHT = 450;
    private static final int MAX_SHAPES = 20;

    private final Random random = new Random();
    private final Pane drawingPane = new Pane();
    private final Label countLabel = new Label("已绘制：0/20");
    private int shapeCount = 0;

    @Override
    public void start(Stage stage) {
        // 创建绘图区并设置大小和边框样式。
        drawingPane.setPrefSize(DRAW_WIDTH, DRAW_HEIGHT);
        drawingPane.setStyle("-fx-background-color: #f8fafc; -fx-border-color: #94a3b8; -fx-border-width: 1;");

        // 点击按钮后创建下一个随机图形。
        Button drawButton = new Button("生成并绘制图形");
        drawButton.setOnAction(event -> createNextShape(drawButton));

        // 顶部区域显示按钮和绘制进度。
        HBox topBar = new HBox(12, drawButton, countLabel);
        topBar.setPadding(new Insets(12));

        // 使用 BorderPane 组织界面布局。
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(drawingPane);

        Scene scene = new Scene(root, 760, 520);
        stage.setTitle("第4题：JavaFX 随机绘图");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 创建并绘制下一个图形。
     * 图形类型由随机数决定，绘制总数达到 20 后停止。
     */
    private void createNextShape(Button drawButton) {
        if (shapeCount >= MAX_SHAPES) {
            drawButton.setDisable(true);
            showInfo("绘图结束", "已经绘制满20个图形，不能继续创建。");
            return;
        }

        // 随机值 0、1、2 分别代表直线、矩形、椭圆。
        int shapeType = random.nextInt(3);
        Shape shape;
        if (shapeType == 0) {
            shape = promptLine();
        } else if (shapeType == 1) {
            shape = promptRectangle();
        } else {
            shape = promptEllipse();
        }

        // 用户取消输入时，本次不绘制图形。
        if (shape == null) {
            return;
        }

        applyRandomStyle(shape);
        drawingPane.getChildren().add(shape);
        shapeCount++;
        countLabel.setText("已绘制：" + shapeCount + "/20");

        if (shapeCount >= MAX_SHAPES) {
            drawButton.setDisable(true);
            showInfo("绘图结束", "已经绘制满20个图形，不能继续创建。");
        }
    }

    /**
     * 提示用户输入直线的起点和终点坐标。
     * 输入格式为：x1 y1 x2 y2
     */
    private Shape promptLine() {
        while (true) {
            Optional<String> input = prompt(
                    "本次图形：直线",
                    "请输入 x1 y1 x2 y2\n坐标范围：x 为 0-" + (int) DRAW_WIDTH + "，y 为 0-" + (int) DRAW_HEIGHT
            );
            if (input.isEmpty()) {
                return null;
            }

            String[] parts = splitInput(input.get());
            if (parts.length != 4) {
                showError("参数数量错误，应输入4个数字。");
                continue;
            }

            try {
                double x1 = parseCoordinate(parts[0], 0, DRAW_WIDTH, "x1");
                double y1 = parseCoordinate(parts[1], 0, DRAW_HEIGHT, "y1");
                double x2 = parseCoordinate(parts[2], 0, DRAW_WIDTH, "x2");
                double y2 = parseCoordinate(parts[3], 0, DRAW_HEIGHT, "y2");
                return new Line(x1, y1, x2, y2);
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    /**
     * 提示用户输入矩形的左上角坐标、宽和高。
     * 输入格式为：x y width height
     */
    private Shape promptRectangle() {
        while (true) {
            Optional<String> input = prompt(
                    "本次图形：矩形",
                    "请输入 x y width height\nx 范围：0-" + (int) DRAW_WIDTH
                            + "，y 范围：0-" + (int) DRAW_HEIGHT
                            + "，宽高必须大于0，且图形不能超出画布。"
            );
            if (input.isEmpty()) {
                return null;
            }

            String[] parts = splitInput(input.get());
            if (parts.length != 4) {
                showError("参数数量错误，应输入4个数字。");
                continue;
            }

            try {
                double x = parseCoordinate(parts[0], 0, DRAW_WIDTH, "x");
                double y = parseCoordinate(parts[1], 0, DRAW_HEIGHT, "y");
                double width = parsePositive(parts[2], "width");
                double height = parsePositive(parts[3], "height");
                if (x + width > DRAW_WIDTH || y + height > DRAW_HEIGHT) {
                    throw new IllegalArgumentException("矩形超出画布范围，请重新输入。");
                }
                return new javafx.scene.shape.Rectangle(x, y, width, height);
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    /**
     * 提示用户输入椭圆的中心点和横纵半径。
     * 输入格式为：centerX centerY radiusX radiusY
     */
    private Shape promptEllipse() {
        while (true) {
            Optional<String> input = prompt(
                    "本次图形：椭圆",
                    "请输入 centerX centerY radiusX radiusY\n中心点范围：x 为 0-" + (int) DRAW_WIDTH
                            + "，y 为 0-" + (int) DRAW_HEIGHT
                            + "，半径必须大于0，且图形不能超出画布。"
            );
            if (input.isEmpty()) {
                return null;
            }

            String[] parts = splitInput(input.get());
            if (parts.length != 4) {
                showError("参数数量错误，应输入4个数字。");
                continue;
            }

            try {
                double centerX = parseCoordinate(parts[0], 0, DRAW_WIDTH, "centerX");
                double centerY = parseCoordinate(parts[1], 0, DRAW_HEIGHT, "centerY");
                double radiusX = parsePositive(parts[2], "radiusX");
                double radiusY = parsePositive(parts[3], "radiusY");
                if (centerX - radiusX < 0 || centerX + radiusX > DRAW_WIDTH
                        || centerY - radiusY < 0 || centerY + radiusY > DRAW_HEIGHT) {
                    throw new IllegalArgumentException("椭圆超出画布范围，请重新输入。");
                }
                return new Ellipse(centerX, centerY, radiusX, radiusY);
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    /**
     * 统一弹出参数输入对话框。
     */
    private Optional<String> prompt(String title, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.setContentText("请输入参数：");
        return dialog.showAndWait();
    }

    /**
     * 将一行输入按空格拆分成多个参数。
     */
    private String[] splitInput(String input) {
        return input.trim().split("\\s+");
    }

    /**
     * 解析坐标值，并检查是否在画布范围内。
     */
    private double parseCoordinate(String rawValue, double min, double max, String fieldName) {
        double value = parseDouble(rawValue, fieldName);
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " 超出范围。");
        }
        return value;
    }

    /**
     * 解析一个必须大于 0 的数值。
     */
    private double parsePositive(String rawValue, String fieldName) {
        double value = parseDouble(rawValue, fieldName);
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " 必须大于0。");
        }
        return value;
    }

    /**
     * 将字符串转换成 double。
     * 如果输入不是数字，则抛出带字段名的异常提示。
     */
    private double parseDouble(String rawValue, String fieldName) {
        try {
            return Double.parseDouble(rawValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " 不是有效数字。");
        }
    }

    /**
     * 为图形设置随机边框颜色和填充颜色。
     * 直线没有填充色，因此单独处理为透明。
     */
    private void applyRandomStyle(Shape shape) {
        Color strokeColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        Color fillColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 0.35);
        shape.setStroke(strokeColor);
        shape.setFill(shape instanceof Line ? Color.TRANSPARENT : fillColor);
        shape.setStrokeWidth(2);
    }

    /**
     * 弹出错误提示框。
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("输入错误");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * 弹出普通信息提示框。
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
