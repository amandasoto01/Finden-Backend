package com.Finden.findenBackEnd.models.entity;

public class Request {
private String res;
private boolean request;
public String getRes() {
	return res;
}
public void setRes(String res) {
	this.res = res;
}
public boolean isRequest() {
	return request;
}
public void setRequest(boolean request) {
	this.request = request;
}
public Request() {
	
}
@Override
public String toString() {
	return "Request [res=" + res + ", request=" + request + "]";
}

}
