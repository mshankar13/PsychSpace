<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>To Do:<hr></h2>
<c:choose>
    <c:when test="${hasEvaluation == true and hasSurvey == true and hasHabit == true}">
        <h3>Good Job! You currently have no todos.</h3>
    </c:when>
    <c:otherwise>
        <!-- Set Goal -->
        <c:if test="${hasGoal == false}">
            <div class="center">
                <a class="button-enroll button fadein" id="button-goal"
                   href="${contextPath}/learn/${courseKey}/goal"><span>Set Goal</span>
                </a>
            </div>
        </c:if>
        <!-- Do Daily Evaluation -->
        <c:if test="${hasEvaluation == false and hasStarted == true}">
            <div class="center">
                <a class="button-enroll button fadein" id="button-evaluation"
                   href="${contextPath}/learn/${courseKey}/evaluation"><span>Daily Evaluation</span>
                </a>
            </div>
        </c:if>
        <!-- Do Survey -->
        <c:if test="${hasSurvey == false}">
            <div class="center">
                <a class="button-enroll button fadein" id="button-survey"
                   href="${contextPath}/learn/${courseKey}/survey"><span>Complete Survey</span>
                </a>
            </div>
        </c:if>
        <%--Do Habit--%>
        <c:if test="${hasHabit == false}">
            <div class="center">
                <a class="button-enroll button fadein" id="button-habit"
                   href="${contextPath}/learn/${courseKey}/habit"><span>Set Habit!</span>
                </a>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>