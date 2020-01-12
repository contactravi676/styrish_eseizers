package com.styrish.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.styrish.connections.JDBCHelper;
import com.styrish.courses.packs.databean.CoursePackageDetailsDataBean;
import com.styrish.shopping.action.OrderItemAddAction;
import com.styrish.shopping.beans.OrderDataBean;
import com.styrish.shopping.beans.OrderItemDataBean;

/**
 * The Class OrdersDAOImpl.This is having all the methods used for order specific operations in the application
 */
public class OrdersDAOImpl extends JDBCHelper {

	/** The Constant CLASSNAME. */
	protected static final String CLASSNAME = "OrdersDAOImpl";

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	/** The Constant CREATE_ORDER_SQL. */
	protected static final String CREATE_ORDER_SQL = "insert into orders (orders_id,users_id,channel_id,status) "
			+ " values (?,?,?,?)";

	/** The Constant CREATE_ORDERITEMS_SQL. */
	protected static final String CREATE_ORDERITEMS_SQL = "INSERT INTO ORDERITEMS (ORDERITEMS_ID,ORDERS_ID,ITEM_CODE,ITEM_ID,TOTALPRODUCTS,DISCOUNT,GRAND_TOTAL) "
			+ "VALUES (?,?,?,?,?,?,?)";

	/** The Constant FETCH_CURRENT_PENDING_ORDER_ID. */
	protected static final String FETCH_CURRENT_PENDING_ORDER_ID = "SELECT orders_id FROM orders WHERE USERS_ID = ? AND STATUS = ?";

	/** The Constant FETCH_NO_OF_ITEMS_IN_CART_SQL. */
	protected static final String FETCH_NO_OF_ITEMS_IN_CART_SQL = "select orderitems.orders_id from orderitems "
			+ "inner join orders on (orderitems.orders_id=orders.orders_id) "
			+ "where orders.users_id=? and orders.status='P'";
	
	/** The Constant FETCH_ORDER_INFO_SQL. */
	protected static final String FETCH_ORDER_INFO_SQL = "SELECT orders_id,users_id,product_total,discount,grand_total,tax "
			+ "from orders where orders_id=?";

	/** The Constant FETCH_ORDER_ITEMS_DETAILS_SQL. */
	protected static final String FETCH_ORDER_ITEMS_DETAILS_SQL = "SELECT orderitems.orderitems_id,orderitems.orders_id,orderitems.item_code,coursepacks.packsdescription,coursepacks.courseSubscription,coursepacks.listprice,coursepacks.offerprice "
			+ "                                                    from orderitems inner join coursepacks on (orderitems.item_id=coursepacks.coursepacks_id) "
			+ "                                                    inner join orders on (orderitems.orders_id=orders.orders_id) where orders.orders_id=?";

	/** The Constant UPDATE_ORDER_OWNER_SQL. */
	protected static final String UPDATE_ORDER_OWNER_SQL = "update orders set users_id=? where orders_id=?";

	/** The Constant UPDATE_ORDER_AMOUNT_SQL. */
	protected static final String UPDATE_ORDER_AMOUNT_SQL = "update orders set product_total=?,discount=?,grand_total=?,tax=? where orders_id=?";
	
	/** The Constant UPDATE_ORDER_ITEM_AMOUNT_SQL. */
	protected static final String UPDATE_ORDER_ITEM_AMOUNT_SQL = "update orderitems set totalproducts=?,discount=?,grand_total=? where orderitems_id=?";

	/** The Constant CANCEL_ORDER_SQL. */
	protected static final String CANCEL_ORDER_SQL = "update orders set status='X' where orders_id=?";

	/** The Constant UPDATE_ORDER_STATUS_SQL. */
	protected static final String UPDATE_ORDER_STATUS_SQL = "update orders set status=? where orders_id=?";
	
	/** The Constant DELETE_ORDER_ITEM_SQL. */
	protected static final String DELETE_ORDER_ITEM_SQL = "delete from orderitems where orderitems_id=?";

	/** The Constant FETCH_ORDER_ITEM_AMOUNTS_SQL. */
	protected static final String FETCH_ORDER_ITEM_AMOUNTS_SQL = "select orderitems.orderitems_id,orderitems.orders_id,orderitems.totalproducts,orderitems.discount,orderitems.grand_total "
			+ "from orderitems inner join orders on (orders.orders_id=orderitems.orders_id) where orders.orders_id=?";

	/** The order data bean. */
	protected OrderDataBean orderDataBean;

	/**
	 * Creates the order.
	 *
	 * @param orderItemAddAction the order item add action
	 */
	public void createOrder(OrderItemAddAction orderItemAddAction) {
		final String METHODNAME = "createOrder";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			long ordersId = System.currentTimeMillis();
			preparedStatement = connection.prepareStatement(CREATE_ORDER_SQL);
			preparedStatement.setLong(1, ordersId);
			preparedStatement.setLong(2, orderItemAddAction.getUserId());
			preparedStatement.setString(3, "DESKTOP");
			preparedStatement.setString(4, "P");
			preparedStatement.execute();
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "New Order Created for User {0}",
					orderItemAddAction.getUserId());

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Update order amount.
	 *
	 * @param orderDataBean the order data bean
	 */
	protected void updateOrderAmount(OrderDataBean orderDataBean) {

		final String METHODNAME = "updateOrderAmounts";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(UPDATE_ORDER_AMOUNT_SQL);
			preparedStatement.setDouble(1, orderDataBean.getProductTotal());
			preparedStatement.setDouble(2, orderDataBean.getOrderDiscount());
			preparedStatement.setDouble(3, orderDataBean.getOrderTotal());
			preparedStatement.setDouble(4, orderDataBean.getOrderTax());
			preparedStatement.setDouble(5, orderDataBean.getOrdersId());
			int rowUpdated = preparedStatement.executeUpdate();

			if (rowUpdated > 0) {
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order Amount has been updated");
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Update order item amount.
	 *
	 * @param orderDataBean the order data bean
	 */
	protected void updateOrderItemAmount(OrderDataBean orderDataBean) {

		final String METHODNAME = "updateOrderItemAmount";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		List<OrderItemDataBean> orderItemDataBeans = orderDataBean.getOrderItemDatabeans();
		try {

			if (orderItemDataBeans != null) {

				Iterator<OrderItemDataBean> itrObj = orderItemDataBeans.iterator();
				preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM_AMOUNT_SQL);
				while (itrObj.hasNext()) {

					OrderItemDataBean itemDataBean = itrObj.next();
					CoursePackageDetailsDataBean coursePackageDetailsDataBean = getCoursePackageDetailsDataBean(
							itemDataBean.getItemId());

					double offerPrice = coursePackageDetailsDataBean.getOfferprice().doubleValue();
					double listPrice = coursePackageDetailsDataBean.getListPrice().doubleValue();
					double discount = 0.00;
					if (listPrice > offerPrice) {
						discount = listPrice - offerPrice;
					}
					preparedStatement.setDouble(1, listPrice);
					preparedStatement.setDouble(2, discount);
					preparedStatement.setDouble(3, offerPrice);
					preparedStatement.setDouble(4, itemDataBean.getOrderItemsId());
					preparedStatement.addBatch();

				}
				int[] noOfItemUpdated = preparedStatement.executeBatch();

				if (noOfItemUpdated != null && noOfItemUpdated.length > 0) {
					LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "No of items updated  " + noOfItemUpdated);
				}

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Update order owner.
	 *
	 * @param orderDataBean the order data bean
	 */
	public void updateOrderOwner(OrderDataBean orderDataBean) {

		final String METHODNAME = "updateOrderOwner";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(UPDATE_ORDER_OWNER_SQL);
			preparedStatement.setLong(1, orderDataBean.getUsersId());
			preparedStatement.setLong(2, orderDataBean.getOrdersId());

			int rowUpdated = preparedStatement.executeUpdate();

			if (rowUpdated > 0) {
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order Owner has been updated");
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Cancel order.
	 *
	 * @param orderDataBean the order data bean
	 */
	
	public void cancelOrder(OrderDataBean orderDataBean) {

		final String METHODNAME = "cancelOrder";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(CANCEL_ORDER_SQL);
			preparedStatement.setLong(1, orderDataBean.getOrdersId());

			int rowUpdated = preparedStatement.executeUpdate();

			if (rowUpdated > 0) {
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order Canceled");
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Update order status.
	 *
	 * @param orderDataBean the order data bean
	 */
	protected void updateOrderStatus(OrderDataBean orderDataBean) {

		final String METHODNAME = "updateOrderStatus";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_SQL);
			preparedStatement.setString(1, String.valueOf(orderDataBean.getStatus()));
			preparedStatement.setLong(2, orderDataBean.getOrdersId());

			int rowUpdated = preparedStatement.executeUpdate();

			if (rowUpdated > 0) {
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order Status Updated");
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Complete order.
	 *
	 * @param orderDataBean the order data bean
	 */
	public void completeOrder(OrderDataBean orderDataBean) {

		final String METHODNAME = "completeOrder";
		updateOrderStatus(orderDataBean);
		LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order {0} Marked As Completed ",
				new Object[] { orderDataBean.getOrdersId() });

	}

	/**
	 * Creates the order item.
	 *
	 * @param orderItemAddAction the order item add action
	 */
	public void createOrderItem(OrderItemAddAction orderItemAddAction) {
		final String METHODNAME = "createOrderItem";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		long orderItemid = System.currentTimeMillis();
		CoursePackageDetailsDataBean coursePackageDetailsDataBean = getCoursePackageDetailsDataBean(
				orderItemAddAction.getProductId());
		double offerPrice = coursePackageDetailsDataBean.getOfferprice().doubleValue();
		double listPrice = coursePackageDetailsDataBean.getListPrice().doubleValue();
		double discount = 0.00;
		if (listPrice > offerPrice) {
			discount = listPrice - offerPrice;
		}
		try {

			preparedStatement = connection.prepareStatement(CREATE_ORDERITEMS_SQL.toLowerCase());
			preparedStatement.setLong(1, orderItemid);
			preparedStatement.setLong(2, orderItemAddAction.getOrdersId());
			preparedStatement.setString(3, orderItemAddAction.getProductType());
			preparedStatement.setLong(4, orderItemAddAction.getProductId());
			preparedStatement.setDouble(5, listPrice);
			preparedStatement.setDouble(6, discount);
			preparedStatement.setDouble(7, offerPrice);

			preparedStatement.execute();
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "New OrderItem Created for User {0} and Order {1}",
					new Object[] { orderItemAddAction.getUserId(), orderItemAddAction.getOrdersId() });
			orderItemAddAction.setOrderItemId(orderItemid);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	/**
	 * Find current pending order for user.
	 *
	 * @param usersId the users id
	 * @return the long
	 */
	public Long findCurrentPendingOrderForUser(Long usersId) {

		final String METHODNAME = "findCurrentPendingOrderForUser";
		Long currentOrderID = null;
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement(FETCH_CURRENT_PENDING_ORDER_ID);
			preparedStatement.setLong(1, usersId);
			preparedStatement.setString(2, "P");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				currentOrderID = resultSet.getLong("ORDERS_ID");

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return currentOrderID;

	}

	/**
	 * Find current pending order for user.
	 *
	 * @param orderDataBean the order data bean
	 * @return the order data bean
	 */
	public OrderDataBean findCurrentPendingOrderForUser(OrderDataBean orderDataBean) {

		final String METHODNAME = "findCurrentPendingOrderForUser";
		Long currentOrderID = null;
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement(FETCH_CURRENT_PENDING_ORDER_ID);
			preparedStatement.setLong(1, orderDataBean.getUsersId());
			preparedStatement.setString(2, "P");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				currentOrderID = resultSet.getLong("ORDERS_ID");

			}

			if (currentOrderID != null) {
				orderDataBean.setOrdersId(currentOrderID);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return orderDataBean;

	}

	/**
	 * Find number of items in current shopping cart.
	 *
	 * @param userId the user id
	 * @return the order data bean
	 */
	public OrderDataBean findNumberOfItemsInCurrentShoppingCart(Long userId) {

		final String METHODNAME = "findNumberOfItemsInCurrentShoppingCart";
		OrderDataBean orderDataBean = new OrderDataBean();
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long ordersId = null;
		int resultCount = 0;
		try {

			preparedStatement = connection.prepareStatement(FETCH_NO_OF_ITEMS_IN_CART_SQL);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ordersId = resultSet.getLong("orders_id");
				resultCount++;
			}

			orderDataBean.setItemCount(resultCount);
			orderDataBean.setOrdersId(ordersId);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return orderDataBean;

	}

	/**
	 * Fetch order details.
	 *
	 * @param orderDataBean the order data bean
	 */
	public void fetchOrderDetails(OrderDataBean orderDataBean) {

		String accessProfile = orderDataBean.getAccessProfile();
		LOGGER.info(" Access Profile to access order data is " + accessProfile);
		if (StringUtils.isNotEmpty(accessProfile) && "OrderItemsData".equalsIgnoreCase(accessProfile)) {
			getOrderItemDetails(orderDataBean);
		} else if (StringUtils.isNotEmpty(accessProfile) && "OrderSummaryData".equalsIgnoreCase(accessProfile)) {
			getOrderDetails(orderDataBean);
		} else if (StringUtils.isNotEmpty(accessProfile) && "OrderFullData".equalsIgnoreCase(accessProfile)) {
			getOrderDetails(orderDataBean);
			getOrderItemDetails(orderDataBean);
		}

		this.setOrderDataBean(orderDataBean);

		return;

	}

	/**
	 * Gets the order item details.
	 *
	 * @param orderDataBean the order data bean
	 * @return the order item details
	 */
	private OrderDataBean getOrderItemDetails(OrderDataBean orderDataBean) {
		final String METHODNAME = "getOrderItemDetails";
		List<OrderItemDataBean> ordItemDataBeans = new ArrayList<OrderItemDataBean>();

		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement(FETCH_ORDER_ITEMS_DETAILS_SQL);
			preparedStatement.setLong(1, orderDataBean.getOrdersId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CoursePackageDetailsDataBean coursePackageDetailsDataBean = new CoursePackageDetailsDataBean();
				OrderItemDataBean orderItemDataBean = new OrderItemDataBean();

				Long orderItemsId = resultSet.getLong("orderitems_id");
				Long ordersId = resultSet.getLong("orders_id");
				String itemCode = resultSet.getString("item_code");
				String packageDescription = resultSet.getString("packsdescription");
				String courseSubscription = resultSet.getString("courseSubscription");
				Double listprice = resultSet.getDouble("listprice");
				Double offerprice = resultSet.getDouble("offerprice");

				coursePackageDetailsDataBean.setPackDescription(packageDescription);
				coursePackageDetailsDataBean.setListPrice(listprice);
				coursePackageDetailsDataBean.setOfferprice(offerprice);
				coursePackageDetailsDataBean.setCourseSubscription(courseSubscription);
				orderItemDataBean.setOrderItemsId(orderItemsId);
				orderItemDataBean.setOrdersId(ordersId);
				orderItemDataBean.setItemCode(itemCode);
				orderItemDataBean.setCoursePackageDetailsDataBean(coursePackageDetailsDataBean);

				ordItemDataBeans.add(orderItemDataBean);
			}

			LOGGER.info("Order Item List Size is" + ordItemDataBeans.size());
			orderDataBean.setOrderItemDatabeans(ordItemDataBeans);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return orderDataBean;

	}

	/**
	 * Gets the order item amount details.
	 *
	 * @param orderDataBean the order data bean
	 * @return the order item amount details
	 */
	private OrderDataBean getOrderItemAmountDetails(OrderDataBean orderDataBean) {
		final String METHODNAME = "getOrderItemDetails";
		List<OrderItemDataBean> ordItemDataBeans = new ArrayList<OrderItemDataBean>();

		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement(FETCH_ORDER_ITEM_AMOUNTS_SQL);
			preparedStatement.setLong(1, orderDataBean.getOrdersId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				OrderItemDataBean orderItemDataBean = new OrderItemDataBean();

				Long orderItemsId = resultSet.getLong("orderitems_id");
				Long ordersId = resultSet.getLong("orders_id");
				Double totalproducts = resultSet.getDouble("totalproducts");
				Double discount = resultSet.getDouble("discount");
				Double grand_total = resultSet.getDouble("grand_total");

				orderItemDataBean.setOrderItemsId(orderItemsId);
				orderItemDataBean.setOrdersId(ordersId);
				orderItemDataBean.setTotalProducts(totalproducts);
				orderItemDataBean.setDiscount(discount);
				orderItemDataBean.setGrandTotal(grand_total);
				ordItemDataBeans.add(orderItemDataBean);
			}

			LOGGER.info("Order Item List Size is" + ordItemDataBeans.size());
			orderDataBean.setOrderItemDatabeans(ordItemDataBeans);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return orderDataBean;

	}

	/**
	 * Gets the order details.
	 *
	 * @param orderDataBean the order data bean
	 * @return the order details
	 */
	private OrderDataBean getOrderDetails(OrderDataBean orderDataBean) {

		final String METHODNAME = "getOrderDetails";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement(FETCH_ORDER_INFO_SQL);
			preparedStatement.setLong(1, orderDataBean.getOrdersId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long ordersId = resultSet.getLong("orders_id");
				Long usersId = resultSet.getLong("users_id");
				Double productTotal = resultSet.getDouble("product_total");
				Double totalDiscount = resultSet.getDouble("discount");
				Double grandTotal = resultSet.getDouble("grand_total");
				Double tax = resultSet.getDouble("tax");

				orderDataBean.setOrdersId(ordersId);
				orderDataBean.setUsersId(usersId);
				orderDataBean.setProductTotal(productTotal);
				orderDataBean.setOrderDiscount(totalDiscount);
				orderDataBean.setOrderTotal(grandTotal);
				orderDataBean.setOrderTax(tax);

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return orderDataBean;

	}

	/**
	 * Gets the order data bean.
	 *
	 * @return the order data bean
	 */
	public OrderDataBean getOrderDataBean() {
		return orderDataBean;
	}

	/**
	 * Sets the order data bean.
	 *
	 * @param orderDataBean the new order data bean
	 */
	public void setOrderDataBean(OrderDataBean orderDataBean) {
		this.orderDataBean = orderDataBean;
	}

	/**
	 * Gets the course package details data bean.
	 *
	 * @param packageId the package id
	 * @return the course package details data bean
	 */
	protected CoursePackageDetailsDataBean getCoursePackageDetailsDataBean(Long packageId) {

		CoursePackageDetailsDataBean coursePackageDetailsDataBean = new CoursePackageDetailsDataBean();
		Map<Long, CoursePackageDetailsDataBean> coursePackageDetailsMap = new HashMap<Long, CoursePackageDetailsDataBean>();
		coursePackageDetailsDataBean.setCoursePackId(packageId);
		coursePackageDetailsDataBean.setPopulatePackDetails(coursePackageDetailsMap);

		if (coursePackageDetailsMap != null && !coursePackageDetailsMap.isEmpty()) {
			coursePackageDetailsMap = coursePackageDetailsDataBean.getPopulatePackDetails();
		}
		coursePackageDetailsDataBean = coursePackageDetailsMap.get(packageId);
		return coursePackageDetailsDataBean;
	}

	/**
	 * Calculate order amounts.
	 *
	 * @param orderDataBean the order data bean
	 * @return the order data bean
	 */
	protected OrderDataBean calculateOrderAmounts(OrderDataBean orderDataBean) {
		setOrderDataBean(orderDataBean);
		getOrderItemAmountDetails(orderDataBean);
		double orderTotal = 0.00;
		double orderDiscount = 0.00;
		double orderProductsTotal = 0.00;
		double orderTax = 0.00;
		List<OrderItemDataBean> orderItemDataBeans = orderDataBean.getOrderItemDatabeans();

		if (orderItemDataBeans != null) {
			Iterator<OrderItemDataBean> iterator = orderItemDataBeans.iterator();

			while (iterator.hasNext()) {

				OrderItemDataBean itemDataBean = iterator.next();
				orderProductsTotal = orderProductsTotal + itemDataBean.getTotalProducts();
				orderDiscount = orderDiscount + itemDataBean.getDiscount().doubleValue();
			}

			orderTotal = orderProductsTotal - orderDiscount;
			orderTax = orderTotal * .2;
			orderTotal = orderTotal + orderTax;
			orderDataBean.setProductTotal(orderProductsTotal);
			orderDataBean.setOrderDiscount(orderDiscount);
			orderDataBean.setOrderTax(orderTax);
			orderDataBean.setOrderTotal(orderTotal);
		}
		return orderDataBean;

	}

	/**
	 * Refresh order amounts.
	 *
	 * @param orderDataBean the order data bean
	 */
	public void refreshOrderAmounts(OrderDataBean orderDataBean) {

		calculateOrderAmounts(orderDataBean);
		updateOrderAmount(orderDataBean);
	}
	
	/**
	 * Delete order item.
	 *
	 * @param orderItemDataBean the order item data bean
	 */
	public void deleteOrderItem(OrderItemDataBean orderItemDataBean) {
		
		final String METHODNAME = "deleteOrderItem";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
	
		try {
	
			preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM_SQL);
			preparedStatement.setLong(1, orderItemDataBean.getOrderItemsId());
	        preparedStatement.execute();
	
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Order Item Deleted");
			
	
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {
	
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
	
			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		
	}

}
