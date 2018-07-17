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
					<dt>${business.value}수익</dt>
					<dd>
						총 액
						<fmt:formatNumber value="${totalIcBu}" pattern="#,###" />
						원
					</dd>
					<dd>
						시술완료
						<fmt:formatNumber value="${completeIcBu}" pattern="#,###" />
						원
					</dd>
					<dd>
						취소
						<fmt:formatNumber value="${cancelIcBu}" pattern="#,###" />
						원
					</dd>
					<dd>
						노쇼
						<fmt:formatNumber value="${noshowIcBu}" pattern="#,###" />
						원
					</dd>
				</dl>

				<dl>
					<dt>건수</dt>
					<dd>총 (${totalCntBu })건</dd>
					<dd>시술완료 (${completeCntBu })건</dd>
					<dd>취소 (${cancelCntBu })건</dd>
					<dd>노쇼 (${noshowCntBu })건</dd>
				</dl>

			</c:if>
		</c:forEach>
	</div>

	<div class="moneyRst">
		<c:forEach var="staff" items="${staffMap}">
			<dl>
				<dt>${staff.value}수익</dt>
				<dd>
					총 액
					<c:forEach var="total" items="${totalIc}">
						<c:if test="${staff.key == total.key}">
							<fmt:formatNumber value="${total.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					원
				</dd>
				<dd>
					시술완료
					<c:forEach var="complete" items="${completeIc}">
						<c:if test="${staff.key == complete.key}">
							<fmt:formatNumber value="${complete.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					원
					<c:forEach var="complete" items="${completeCnt}">
						<c:if test="${staff.key == complete.key}">
							(<fmt:formatNumber value="${complete.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					건
				</dd>
				<dd>
					취소
					<c:forEach var="cancel" items="${cancelIc}">
						<c:if test="${staff.key == cancel.key}">
							<fmt:formatNumber value="${cancel.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					원
					<c:forEach var="cancel" items="${cancelCnt}">
						<c:if test="${staff.key == cancel.key}">
							(<fmt:formatNumber value="${cancel.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					건
				</dd>
				<dd>
					노쇼
					<c:forEach var="noshow" items="${noshowIc}">
						<c:if test="${staff.key == noshow.key}">
							<fmt:formatNumber value="${noshow.value}" pattern="#,###" />
						</c:if>
					</c:forEach>
					원
					<c:forEach var="noshow" items="${noshowCnt}">
						<c:if test="${staff.key == noshow.key}">
							(<fmt:formatNumber value="${noshow.value}" pattern="#,###" />)
						</c:if>
					</c:forEach>
					건
				</dd>
			</dl>
		</c:forEach>
	</div>
</body>
</html>