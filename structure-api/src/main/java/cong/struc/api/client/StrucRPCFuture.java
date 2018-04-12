package cong.struc.api.client;

import com.nhb.common.async.RPCFuture;
import com.nhb.common.async.translator.RPCFutureTranslator;
import com.nhb.common.data.PuElement;

import cong.struc.api.message.StrucMessageHelper;
import cong.struc.api.message.StrucResponse;

public class StrucRPCFuture extends RPCFutureTranslator<PuElement, StrucResponse> {

	public StrucRPCFuture(RPCFuture<PuElement> future) {
		super(future);
	}

	@Override
	protected StrucResponse translate(PuElement sourceResult) throws Exception {
		return StrucMessageHelper.deserialize(sourceResult);
	}

}
