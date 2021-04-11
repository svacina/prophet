package edu.baylor.ecs.cloudhubs.examiner.services;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;

import java.util.Optional;

public class VisitComment {

    public static void detect(ClassOrInterfaceDeclaration n, String path) {
        Optional<Comment> commentOptional = n.getComment();
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            String str = comment.toString();
            if (str.contains("IN")) {

            }
        }


//        if (n.getName().toString().contains("TrainType")){
//            System.out.println();
//            if (commentOptional.isPresent()) {
//                Comment comment = commentOptional.get();
//                String str = comment.toString();
//                System.out.println(str);
//            }
//        }
    }

    public static void detect(FieldDeclaration n, String path) {
        Optional<Comment> commentOptional = n.getComment();
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            String str = comment.toString();
            if (str.contains("IN")) {
                System.out.println(str);
                String[] split = str.split(" ");
                if (n.getVariables().size() > 0) {
                    VariableDeclarator vd = n.getVariables().get(0);
                    String variableName = vd.getNameAsString();
                    System.out.println(variableName);
                    if (vd.getType() != null) {
                        System.out.println(vd.getTypeAsString());
                    }
                }
            }
        }
    }


    public static void detect(MethodDeclaration n, String path) {
//        Optional<Comment> commentOptional = n.getComment();
//        if (commentOptional.isPresent()) {
//            Comment comment = commentOptional.get();
//            String str = comment.toString();
//            if (str.contains("CC")) {
//                System.out.println(str);
//                if (n.getVariables().size() > 0) {
//                    VariableDeclarator vd = n.getVariables().get(0);
//                    String variableName = vd.getNameAsString();
//                    System.out.println(variableName);
//                    if (vd.getType() != null) {
//                        System.out.println(vd.getTypeAsString());
//                    }
//                }
//            }
//        }
    }

}
