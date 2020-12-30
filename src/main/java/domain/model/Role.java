package domain.model;

public enum Role {
        customer("customer"),
        administrator("administrator"),
        guest("guest");

        private String stringValue;

        private Role(String stringValue){
            this.stringValue = stringValue;
        }

        public String getStringValue(){
            return stringValue;
        }
}
