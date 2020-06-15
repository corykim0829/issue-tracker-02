package com.codesquad.issuetracker.label.data;

import com.codesquad.issuetracker.issue.data.IssueLabelRelation;
import com.codesquad.issuetracker.label.web.model.LabelQuery;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Label {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private String color;

  @JsonInclude(Include.NON_NULL)
  @OneToMany(mappedBy = "label", fetch = FetchType.EAGER)
  private List<IssueLabelRelation> issueLabelRelations = new ArrayList<>();

  public static Label of(Long id) {
    return Label.builder()
        .id(id)
        .build();
  }

  public static Label extractMainInform(Label label) {
    return Label.builder()
        .id(label.getId())
        .title(label.getTitle())
        .description(label.getDescription())
        .color(label.getColor())
        .build();
  }

  public static Label from(LabelQuery labelQuery) {
    return Label.builder()
        .title(labelQuery.getTitle())
        .description(labelQuery.getDescription())
        .color(labelQuery.getColor())
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Label)) {
      return false;
    }
    Label label = (Label) o;
    return Objects.equals(getId(), label.getId()) &&
        Objects.equals(getTitle(), label.getTitle()) &&
        Objects.equals(getDescription(), label.getDescription()) &&
        Objects.equals(getColor(), label.getColor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDescription(), getColor());
  }
}
