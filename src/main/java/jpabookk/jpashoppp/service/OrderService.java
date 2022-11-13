package jpabookk.jpashoppp.service;

import jpabookk.jpashoppp.domain.Delivery;
import jpabookk.jpashoppp.domain.Member;
import jpabookk.jpashoppp.domain.Order;
import jpabookk.jpashoppp.domain.OrderItem;
import jpabookk.jpashoppp.domain.item.Item;
import jpabookk.jpashoppp.repository.ItemRepository;
import jpabookk.jpashoppp.repository.MemberRepository;
import jpabookk.jpashoppp.repository.OrderRepository;
import jpabookk.jpashoppp.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order); // cascade = CascadeType.ALL delivery orderitem 덕분에 다 저장됨

        return order.getId();
    }

    /**
     * 주문 취소
     */

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancle();
    }

    //검색

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }


}
