package com.styrish.courses.packs.databean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.courses.packs.dao.CoursePacksDao;

public class CoursePackageDetailsDataBean {

	private Long coursePackId;
	private Long courseId;
	private String packName;
	private String packDescription;
	private String packLongDescription;
	private String courseSubscription;
	private String imagePath;
	private Double listPrice;
	private Double offerprice;
	private String packageContents;
	private Long subCourseId;
	private String status;
	private Timestamp createdDate;
	private Map<Long, CoursePackageDetailsDataBean> populatePackDetails;
	protected static final String CLASSNAME = "CoursePackageDetailsDataBean";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public Long getCoursePackId() {
		return coursePackId;
	}

	public void setCoursePackId(Long coursePackId) {
		this.coursePackId = coursePackId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getPackDescription() {
		return packDescription;
	}

	public void setPackDescription(String packDescription) {
		this.packDescription = packDescription;
	}

	public String getPackLongDescription() {
		return packLongDescription;
	}

	public void setPackLongDescription(String packLongDescription) {
		this.packLongDescription = packLongDescription;
	}

	public String getCourseSubscription() {
		return courseSubscription;
	}

	public void setCourseSubscription(String courseSubscription) {
		this.courseSubscription = courseSubscription;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getOfferprice() {
		return offerprice;
	}

	public void setOfferprice(Double offerprice) {
		this.offerprice = offerprice;
	}

	public String getPackageContents() {
		return packageContents;
	}

	public void setPackageContents(String packageContents) {
		this.packageContents = packageContents;
	}

	public Long getSubCourseId() {
		return subCourseId;
	}

	public void setSubCourseId(Long subCourseId) {
		this.subCourseId = subCourseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Map<Long, CoursePackageDetailsDataBean> getPopulatePackDetails() {
		return populatePackDetails;
	}

	public void setPopulatePackDetails(Map<Long, CoursePackageDetailsDataBean> populatePackDetails) {
		final String METHODNAME = "setPopulatePackDetails";
		CoursePackageDetailsDataBean coursePackageDetailsDataBean = null;

		if (populatePackDetails == null) {
			populatePackDetails = new HashMap<Long, CoursePackageDetailsDataBean>();
		}
		StringBuilder cacheIdentifier = new StringBuilder("coursePackageDetailsDataBeanCacheObject").append("_")
				.append(getCoursePackId());
		Object identifierObj = cacheIdentifier.toString();

		CachedObject o = (CachedObject) CacheManager.getCache(identifierObj.toString());
		if (o == null) {
			CoursePacksDao coursePacksDao = new CoursePacksDao();
			coursePackageDetailsDataBean = coursePacksDao.fetchPackageDetails(getCoursePackId());
			LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "Fetched from Database" + coursePackageDetailsDataBean);

			CachedObject cachedObject = new CachedObject(coursePackageDetailsDataBean, identifierObj, 180);
			CacheManager.putCache(cachedObject);
		} else {
			/* get the object from cache */
			coursePackageDetailsDataBean = (CoursePackageDetailsDataBean) o.object;

		}

		populatePackDetails.put(getCoursePackId(), coursePackageDetailsDataBean);

		this.populatePackDetails = populatePackDetails;
	}

}
