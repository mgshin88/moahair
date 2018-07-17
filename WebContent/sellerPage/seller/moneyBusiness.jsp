<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

</head>
<body>
	<div class="moneyRst">

		<c:forEach var="business" items="${businessName}">
			<c:if test="${business.key == bs_num}">
				<dl>
					<dt>${business.value}����</dt>
					<dd>
						�� ��
						<fmt:formatNumber value="${totalIcBu}" pattern="#,###" />
						��
					</dd>
					<dd>
						�ü��Ϸ�
						<fmt:formatNumber value="${completeIcBu}" pattern="#,###" />
						��
					</dd>
					<dd>
						���
						<fmt:formatNumber value="${cancelIcBu}" pattern="#,###" />
						��
					</dd>
					<dd>
						���
						<fmt:formatNumber value="${noshowIcBu}" pattern="#,###" />
						��
					</dd>
				</dl>

				<dl>
					<dt>�Ǽ�</dt>
					<dd>�� (${totalCntBu })��</dd>
					<dd>�ü��Ϸ� (${completeCntBu })��</dd>
					<dd>��� (${cancelCntBu })��</dd>
					<dd>��� (${noshowCntBu })��</dd>
				</dl>

			</c:if>
		</c:forEach>
	</div>

	<div class="moneyRst">
		<c:forEach var="staff" items="${staffMap}">
			<dl>
				<dt>${staff.value}����</dt>
				<dd>
					�� ��
					<c:forEach var="total" items="${totalIc}">
						<c:if test="${staff.key == total.key}">
							<fmt:formatNumber value="${total.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					��
				</dd>
				<dd>
					�ü��Ϸ�
					<c:forEach var="complete" items="${completeIc}">
						<c:if test="${staff.key == complete.key}">
							<fmt:formatNumber value="${complete.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					��
					<c:forEach var="complete" items="${completeCnt}">
						<c:if test="${staff.key == complete.key}">
							(<fmt:formatNumber value="${complete.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					��
				</dd>
				<dd>
					���
					<c:forEach var="cancel" items="${cancelIc}">
						<c:if test="${staff.key == cancel.key}">
							<fmt:formatNumber value="${cancel.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					��
					<c:forEach var="cancel" items="${cancelCnt}">
						<c:if test="${staff.key == cancel.key}">
							(<fmt:formatNumber value="${cancel.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					��
				</dd>
				<dd>
					���
					<c:forEach var="noshow" items="${noshowIc}">
						<c:if test="${staff.key == noshow.key}">
							<fmt:formatNumber value="${noshow.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					��
					<c:forEach var="noshow" items="${noshowCnt}">
						<c:if test="${staff.key == noshow.key}">
							(<fmt:formatNumber value="${noshow.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					��
				</dd>
			</dl>
		</c:forEach>
	</div>
</body>
</html>