package pro.hexa.study.backend.todo.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ParentTodo extends Todo {

    private List<ChildTodo> details;

    public ParentTodo(String title, String content, Short timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn) {
        super(title, content, timeToTakeInMinutes, startAt, completeYn);
        this.details = Collections.emptyList();
    }

    public List<ChildTodo> getDetails() {
        return this.details;
    }

    public void addDetailTodo(ChildTodo detail) {
        details.add(detail);
        detail.setParentTodo(this);
         Short s = (short) (this.timeToTakeInMinutes + detail.getTimeToTakeInMinutes());
         this.timeToTakeInMinutes = s;
        // todo: details에 detail을 추가하고, detail의 parent로 지금 이 parentTodo를 지정하기(parent가 이 객체가 아닐때만).
        // todo: parentTodo의 걸리는 시간은 details의 걸리는 시간의 합과 같도록 항상 유지해줘야한다.
    }

    public void deleteDetailTodo(ChildTodo detail) {
        this.details.remove(detail);
        detail.rmParent();
        this.timeToTakeInMinutes = (short)(this.timeToTakeInMinutes - detail.timeToTakeInMinutes);
        // todo: detail을 details에서 삭제하고, detail의 parent에서도 지금 이 parentTodo를 삭제하기.
        // todo: parentTodo의 걸리는 시간은 details의 걸리는 시간의 합과 같도록 항상 유지해줘야한다.
    }

    public void completeTodo(ChildTodo childTodo) {
        childTodo.completeTodo();
        boolean allCompleted = true;
        for(int i = 0; i < this.details.size(); i++){
            if(!this.details.get(i).isCompleteYn()){
                allCompleted = false;
            }
        }
        if(allCompleted){
            this.isCompleteYn();
        }
        // todo: 입력 받은 childTodo의 완료 처리와 동시에 parentTodo의 모든 details 내에 있는 todo가 완료 되었으면 parentTodo도 완료 처리.
    }
    public List<Long> getIdList(){
        List<Long> IdList = Collections.emptyList();
        IdList.add(this.getId());
        for(int i = 0; i < this.details.size(); i++){
            IdList.add(this.details.get(i).getId());
        }
        return IdList;
    }

    public List<String> getSubTitles(){
        List<String> subTitles = Collections.emptyList();
        for(int i = 0; i < this.details.size(); i++){
            subTitles.add(this.details.get(i).getTitle());
        }
        return subTitles;
    }
    public List<String> getDetailContents(){
        List<String> detailContents = Collections.emptyList();
        for(int i = 0; i < this.details.size(); i++){
            detailContents.add(this.details.get(i).getContent());
        }
        return detailContents;
    }


}
