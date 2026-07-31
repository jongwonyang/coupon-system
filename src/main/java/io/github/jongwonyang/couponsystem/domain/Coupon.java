package io.github.jongwonyang.couponsystem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int totalQuantity; // 총 발행 가능한 쿠폰 수량

    @Column(nullable = false)
    private int issuedQuantity; // 현재까지 발급된 수량

    @Column(nullable = false)
    private LocalDateTime dateStart;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    public Coupon(String title, int totalQuantity, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.title = title;
        this.totalQuantity = totalQuantity;
        this.issuedQuantity = 0;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    // 쿠폰 발급 비즈니스 로직
    public void issue() {
        if (issuedQuantity >= totalQuantity) {
            throw new IllegalStateException("쿠폰 수량이 모두 소진되었습니다.");
        }
        this.issuedQuantity++;
    }
}
