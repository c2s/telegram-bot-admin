package cc.mrbird.system.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_telegram_message")
public class TelegramMessage implements Serializable {

    /**
     * 消息状态
     */
    public static final String STATUS_VALID = "1";

    public static final String STATUS_LOCK = "0";

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created ")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    /**
     * @return id
     */
    public Long getMessageId() { return id; }

    /**
     *
     * @param id
     */
    public void setMessageId(Long id) { this.id = id; }

    /**
     * @return status
     */
    public Integer getStatus() { return status; }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status == null ? null : 0;
    }


    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() { return updated; }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) { this.updated = updated; }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
