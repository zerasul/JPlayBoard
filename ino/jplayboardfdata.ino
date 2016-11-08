#include <ArduinoJson.h>

#include <NanoPlayBoard.h>

NanoPlayBoard board;

StaticJsonBuffer<200> jsonBuffer;
JsonObject& state = jsonBuffer.createObject();
JsonObject& buttons=jsonBuffer.createObject();
JsonObject& acc=jsonBuffer.createObject();

char data[200];
bool initiated=false;
String str;
int buzzerTone;
void setup() {
  Serial.begin(9600);

  while(!Serial){
    
  }
}

void loop() {
  if(Serial.available()){
    str=Serial.readString();
    
    str.toCharArray(data,str.length());
    JsonObject& jsonrecv=jsonBuffer.parseObject(data);

    if(jsonrecv.success())
      makeAction(jsonrecv);
     
  }
  
  if(initiated){
    getCurrentState();
  }
  delay(500);
}
void makeAction(JsonObject& jsonrecv){
  char* ctype=jsonrecv["type"];
  String type(ctype);
  
  if(type.equals("init")){
    initiated=true;
  }else{
    if(type.equals("dinit")){
      initiated=false;
    }else{
      if(type.equals("rgb")){
        int r=jsonrecv["params"]["r"];
        int g=jsonrecv["params"]["g"];
        int b=jsonrecv["params"]["b"];
        board.rgb.setColor(r,g,b);
      }else{
        if(type.equals("buzzer")){
          buzzerTone = jsonrecv["params"]["val"];
          
        }else{
          if(type.equals("sbuzzer")){
            buzzerTone=0;
          }else{
            if(type.equals("matrix")){
              char* character=jsonrecv["params"]["val"];
              board.ledmatrix.print(character[0]);
            }
          }
        }
      }
    }
  }
    if(buzzerTone>0)
      board.buzzer.playTone(buzzerTone);
  
}
void getCurrentState(){

  String state="";
  state=state+board.ldr.read()+"\t";
  state=state+board.potentiometer.read()+"\t";
  state=state+board.buttons.left.isPressed()+"\t";
  state=state+board.buttons.right.isPressed()+"\t";
  state=state+board.buttons.down.isPressed()+"\t";
  state=state+board.buttons.top.isPressed()+"\t";
  float humidity=board.dht.readHumidity();
  if(!isnan(humidity))
    state=state+humidity+"\t";
  else{
    state = state+"NaN\t";
  }
  float distance=board.ultrasound.pingCm();  
  if(!isnan(distance)){
    state=state+distance+"\t";
  }else{
    state=state+"NaN\t";
  }
  /*state["ldr"]=board.ldr.read();
  state["potentiometer"]=board.potentiometer.read();
  
  buttons["left"]=board.buttons.left.isPressed();
  buttons["right"]=board.buttons.right.isPressed();
  buttons["top"]=board.buttons.top.isPressed();
  buttons["down"]=board.buttons.down.isPressed();
  state["buttons"]=buttons;
  float humidity=board.dht.readHumidity();
  if(!isnan(humidity))
    state["humidity"]=humidity;
  state["distance"]=board.ultrasound.pingCm();
  state["position"]=board.rotaryencoder.getPosition();
   uint8_t x = board.accelerometer.getX();
  uint8_t y = board.accelerometer.getY();
  uint8_t z = board.accelerometer.getZ();
  acc["x"]=x;
  acc["y"]=y;
  acc["z"]=z;
  state["accelerometer"]=acc;
  state.printTo(Serial);
  */
  Serial.println(state);
}

